package com.manche.springsecurityjwt6;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(type = SecuritySchemeType.HTTP,name = "bearerAuth",
				scheme = "bearer",
				bearerFormat = "JWT"
)
@OpenAPIDefinition(info = @Info(title ="Customer API",
		version = "1.0",
		description = "API desarrollada por Omar Pimentel"

		), security = {@SecurityRequirement(name = "bearerAuth")})
public class SpringSecurityJwt6Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwt6Application.class, args);
	}

}
