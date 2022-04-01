package io.github.julianobrl.seritycommon.security.token;

import java.io.InvalidObjectException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import io.github.julianobrl.seritycommon.entitys.User;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class TokenCreator {
	
	@SneakyThrows
	public static SignedJWT createSignedJWT(Authentication auth,int expiration) {
		
		log.info("Creating JWS");
		JWTClaimsSet dataSet = null;
		if(auth.getPrincipal() instanceof User) {
			User user = (User) auth.getPrincipal();
			dataSet = createClaimsSet(auth,user,expiration);
		}		
		if(dataSet == null) {
			throw new InvalidObjectException("JWTClaimsSet is null -> auth.getPrincipal() must be type User or Company"); 
		}
		
		KeyPair keyPair = generateKeyPair();
		
		log.info("Creating JWK");
		JWK jwk = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic()).keyID(UUID.randomUUID().toString()).build();
		SignedJWT token = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).jwk(jwk).type(JOSEObjectType.JWT).build(), dataSet);
		
		log.info("Signing token");
		RSASSASigner rsassa = new RSASSASigner(keyPair.getPrivate());
		token.sign(rsassa);
		
		log.info("Serialized token -> "+token.serialize());
		
		return token;
	}
	
	private static JWTClaimsSet createClaimsSet(Authentication auth,User user,int expiration) {
		log.info("Creating clains sets.");
		return new JWTClaimsSet.Builder()
				.subject(user.getUsername())
				.claim("authorities", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.claim("id", user.getId().toString())
				.claim("type", "USER")
				.issuer("https://github.com/julianoBRL")
				.issueTime(new Date())
				.expirationTime(new Date(System.currentTimeMillis()+(expiration*1000)))
				.build();
	}
	
	@SneakyThrows
	private static KeyPair generateKeyPair() {
		log.info("Creating keyPair.");
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		return generator.genKeyPair();
	}
	
	public static String encryptToken(SignedJWT token,String privateKey) throws JOSEException {
		log.info("Encrypting token");
		DirectEncrypter directEncrypter = new DirectEncrypter(privateKey.getBytes());
		
		JWEObject jwe = new JWEObject(
				new JWEHeader.Builder(JWEAlgorithm.DIR,EncryptionMethod.A128CBC_HS256).contentType("JWT").build(),
				new Payload(token)
				);
		
		jwe.encrypt(directEncrypter);
		
		return jwe.serialize();
	}
}
