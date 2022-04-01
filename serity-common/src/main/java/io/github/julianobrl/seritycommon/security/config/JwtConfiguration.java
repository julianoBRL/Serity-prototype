package io.github.julianobrl.seritycommon.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "jwt.config")
public class JwtConfiguration {
	
	@NestedConfigurationProperty
	private Header header = new Header();
	private String loginUrl = "/login/**";
	private int expiration = 3600;
	private String privateKey = "yJKsbsc3qiS8JvPTEBwEU8sWDfqNpGeZ";
	private String type = "encripted";
	
	@Getter
	@Setter
	public static class Header {
		private String name = "Authorization";
		private String prefix = "Bearer ";
	}
}
