package br.com.elo7.sonda.candidato.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.elo7.sonda.candidato.exception.BadRequestException;
import br.com.elo7.sonda.candidato.exception.Error;
import br.com.elo7.sonda.candidato.exception.InternalServerException;
import br.com.elo7.sonda.candidato.exception.NotFoundException;
import br.com.elo7.sonda.candidato.exception.UnprocessableEntityException;

@ControllerAdvice
//TODO LOG JSON
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Error> badRequestError(BadRequestException ex, WebRequest request) {

		Error error = Error
				.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(ex.getMessage())
				.validationErrors(ex.getValidationErrors())
				.build();

		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Error> notFoundError(Exception ex, WebRequest request) {

		Error error = Error
				.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value())
				.error(HttpStatus.NOT_FOUND.getReasonPhrase())
				.message(ex.getMessage())
				.build();

		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnprocessableEntityException.class)
	public ResponseEntity<Error> unprocessableEntityError(UnprocessableEntityException ex, WebRequest request) {

		Error error = Error
				.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.error(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
				.message(ex.getMessage())
				.validationErrors(ex.getValidationErrors())
				.build();

		return new ResponseEntity<Error>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<Error> internalServerError(Exception ex, WebRequest request) {

		Error error = Error
				.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.message(ex.getMessage())
				.build();
		

		return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Error> badRequestError(MissingRequestHeaderException ex, WebRequest request) {
             
    	Error error = Error
				.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(ex.getMessage())
				//.validationErrors(ex.getValidationErrors())
				.build();

		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    }
    
	
	 @Override
	   protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
			Error error = Error
					.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
					.message(ex.getMessage())
					.build();

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	   }

    
	 @Override
	   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
			Error error = Error
					.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
					.message(ex.getMessage())
					.build();

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	   }

}
