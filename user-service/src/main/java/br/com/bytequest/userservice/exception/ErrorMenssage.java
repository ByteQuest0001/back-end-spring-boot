package br.com.bytequest.userservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMenssage {
	private String dateTime;
	private String path;
	private String metodo;
	private int status;
	private String statusText;
	private String message;
	private Map<String, String> errors;

	public ErrorMenssage() {
	}

	public ErrorMenssage(String dateTime, HttpStatus status, String message, HttpServletRequest request) {
		this.dateTime = dateTime;
		this.path = request.getRequestURI();
		this.metodo = request.getMethod();
		this.status = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
	}

	public ErrorMenssage(String dateTime, HttpStatus status, String message, HttpServletRequest request, BindingResult result) {
		this.dateTime = dateTime;
		this.path = request.getRequestURI();
		this.metodo = request.getMethod();
		this.status = status.value();
		this.statusText = status.getReasonPhrase();
		this.message = message;
		addErrors(result);
	}

	public void addErrors(BindingResult result) {
		this.errors = new HashMap<>();
		for (FieldError fieldError : result.getFieldErrors()) {
			this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ErrorMenssage that = (ErrorMenssage) o;
		return status == that.status && Objects.equals(dateTime, that.dateTime) && Objects.equals(path, that.path) && Objects.equals(metodo, that.metodo) && Objects.equals(statusText, that.statusText) && Objects.equals(message, that.message) && Objects.equals(errors, that.errors);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, path, metodo, status, statusText, message, errors);
	}

	@Override
	public String toString() {
		return "ErrorMenssage{" +
			"dateTime='" + dateTime + '\'' +
			", path='" + path + '\'' +
			", metodo='" + metodo + '\'' +
			", status=" + status +
			", statusText='" + statusText + '\'' +
			", message='" + message + '\'' +
			", errors=" + errors +
			'}';
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
