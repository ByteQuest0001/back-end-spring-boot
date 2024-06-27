package br.com.bytequest.userservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI myOpenAPI() {
		Info info = new Info()
			.title("Serviço Usuario")
			.version("1.0")
			.description("Serviço que vai cuidar da entidade usuario");

		return new OpenAPI().info(info);
	}
}
