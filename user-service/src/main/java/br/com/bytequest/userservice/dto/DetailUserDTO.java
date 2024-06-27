package br.com.bytequest.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "Detalhe do Usuario")
public record DetailUserDTO(

	@Schema(title = "ID do usuario", minimum = "1", type="long", example = "1")
	Long id,

	@Schema(title = "Nome", minLength = 3, maxLength = 100, type = "string", example = "Ana Beatriz")
	String name,

	@Schema(title = "Email", minLength = 3, maxLength = 150, type = "string", example = "email@gmail.com")
	String email
) {
}
