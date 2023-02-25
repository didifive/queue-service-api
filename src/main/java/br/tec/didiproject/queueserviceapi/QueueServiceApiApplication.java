package br.tec.didiproject.queueserviceapi;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiAnnotations.SECURITY_SCHEME_BEARER_AUTH;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiAnnotations.SECURITY_SCHEME_DESCRIPTION;

@SpringBootApplication
@SecurityScheme(name = SECURITY_SCHEME_BEARER_AUTH
		, description = SECURITY_SCHEME_DESCRIPTION
		, scheme = "bearer"
		, bearerFormat = "JWT"
		, type = SecuritySchemeType.HTTP
		, in = SecuritySchemeIn.HEADER)
public class QueueServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueueServiceApiApplication.class, args);
	}

}
