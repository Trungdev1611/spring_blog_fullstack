package com.blog.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
// @SpringBootApplication(exclude = SecurityAutoConfiguration.class) // tạm thời
// tắt spring security
// http://localhost:8080/swagger-ui/index.html
@OpenAPIDefinition(info = @Info(title = "Springboot rest API documentation Udemy", description = "Sử dụng annotation OpenAPIDefinition trong file main app để viết document", version = "v1.0", contact = @Contact(name = "Trung", email = "trungdev1611@gmail.com", url = "http://localhost:8080"), license = @License(name = "Apache 2.0", url = "http://localhost:8080/license")))
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
