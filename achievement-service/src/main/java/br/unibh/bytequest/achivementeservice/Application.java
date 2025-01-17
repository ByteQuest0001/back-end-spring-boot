package br.unibh.bytequest.achivementeservice;

import br.unibh.bytequest.achivementeservice.persistencia.DynamoDBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

/**
 * Classe executável que inicia a aplicação Spring Boot
 * @author jhcru
 *
 */
@SpringBootApplication
@Import({DynamoDBConfig.class})
@OpenAPIDefinition(info = @Info(title = "Serviço de cadastro de conquistas", version = "1.0", description = "API de Serviço de cadastro de conquistas",
	license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"),
	contact = @Contact(name = "Suporte da Empresa IntellyT", email = "suporte@intellyt.com"),
	termsOfService = "http://intellyt.com/termos_uso_api")	)
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		log.info("Inicializando...");
	    System.setProperty("server.servlet.context-path", "/");
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
	}
	
}
