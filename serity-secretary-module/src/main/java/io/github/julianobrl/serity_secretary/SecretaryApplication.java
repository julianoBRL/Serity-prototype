package io.github.julianobrl.serity_secretary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("io.github.julianobrl.seritycommon")
@ComponentScan({"io.github.julianobrl.seritycommon",
		"io.github.julianobrl.serity_secretary"})
@EnableJpaRepositories("io.github.julianobrl.seritycommon")
public class SecretaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretaryApplication.class, args);
	}

}
