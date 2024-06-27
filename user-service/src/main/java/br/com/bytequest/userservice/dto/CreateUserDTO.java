package br.com.bytequest.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(title = "Cadastro de Usuario")
public record CreateUserDTO(

	@Schema(title = "Nome", minLength = 3, maxLength = 100,  type = "string",example = "Ana Beatriz")
	@NotBlank
	@Size(min = 3, max = 100)
	String name,

	@Schema(title = "Email", minLength = 3, maxLength = 150,  type = "string", example = "email@gmail.com")
	@NotBlank
	@Email(message = "Informe um email valido")
	@Size(min = 3, max = 150)
	String email,

	@Schema(title = "Senha", minLength = 3, maxLength = 100,  type = "string", example = "1234")
	@Size(min = 3, max = 100)
	@NotBlank
	String password_hash
) {
}
