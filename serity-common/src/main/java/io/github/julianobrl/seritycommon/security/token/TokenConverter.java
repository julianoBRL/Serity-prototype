package io.github.julianobrl.seritycommon.security.token;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;

import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;

import io.github.julianobrl.seritycommon.security.config.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenConverter {
	
	@SneakyThrows
	public String decryptToken(String encryptedToken,String privateKey) {
		log.info("Decrypting token");
		JWEObject jweObject = JWEObject.parse(encryptedToken);
		DirectDecrypter directDecrypter  = new DirectDecrypter(privateKey.getBytes());
		jweObject.decrypt(directDecrypter);
		log.info("Token decrypted");
		return jweObject.getPayload().toSignedJWT().serialize();
	}
	
	@SneakyThrows
	public void validateTokenSign(String signedToken) {
		log.info("Validating token");
		SignedJWT signedJWT = SignedJWT.parse(signedToken);
		RSAKey publicKey = RSAKey.parse(signedJWT.getHeader().getJWK().toJSONObject());
		if(!signedJWT.verify(new RSASSAVerifier(publicKey)))
			throw new AccessDeniedException("Não foi possivel validar o token");
	}
	
}