package br.tec.didiproject.queueserviceapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SECURITY_SCHEME;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SECURITY_SCHEME_BEARER_FORMAT;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SECURITY_SCHEME_DESCRIPTION;
import static br.tec.didiproject.queueserviceapi.enums.constants.OpenApiSchemes.SECURITY_SCHEME_NAME;

@SpringBootApplication
@SecurityScheme(name = SECURITY_SCHEME_NAME
		, description = SECURITY_SCHEME_DESCRIPTION
		, scheme = SECURITY_SCHEME
		, bearerFormat = SECURITY_SCHEME_BEARER_FORMAT
		, type = SecuritySchemeType.HTTP
		, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
public class QueueServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueueServiceApiApplication.class, args);
	}

}
