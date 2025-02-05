package com.bank.account;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@OpenAPIDefinition(
		info = @Info(
				title = "Account microservice - Rest API Documentation",
				description = "Bank Account microservice - Rest API Documentation -"
				+"you can create,update,fetch and delete account" ,
				version = "1.0" ,
				contact = @Contact(
						name = "Fouad Adel Fouad",
						email = "fouadadel905@gmail.com"
				)
		)


)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
