package br.com.bytequest.userservice.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMenssage> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result) {
		var dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm"));
		var errorMenssage = new ErrorMenssage(dateTime, HttpStatus.UNPROCESSABLE_ENTITY, "Campos Inválidos", request, result);

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(errorMenssage);
	}

	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseEntity<ErrorMenssage> unexpectedTypeException(UnexpectedTypeException ex, HttpServletRequest request, BindingResult result) {
		var dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm"));
		var errorMenssage = new ErrorMenssage(dateTime, HttpStatus.UNPROCESSABLE_ENTITY, "Tipo dos campos são Inválidos", request, result);

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(errorMenssage);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorMenssage> unexpectedTypeException(DataIntegrityViolationException ex, HttpServletRequest request) {
		var dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm"));
		var errorMenssage = new ErrorMenssage(dateTime, HttpStatus.UNPROCESSABLE_ENTITY, "Violação de integridade de dados no banco de dados", request);

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(errorMenssage);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorMenssage> unexpectedTypeException(EntityNotFoundException ex, HttpServletRequest request) {
		var dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm"));
		var errorMenssage = new ErrorMenssage(dateTime, HttpStatus.NOT_FOUND, "Não foi encontrado uma entidade com esse ID", request);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorMenssage);
	}
}
