package com.ticketbooking.main.ExceptionHandlers;

import java.time.LocalDateTime;

import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.ticketbooking.main.dao.ErrorMessage;



@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PropertyValueException.class) 
	public ResponseEntity<Object> handleUserNotFoundException(DataIntegrityViolationException ex){
		return new ResponseEntity<>(new ErrorMessage("Null value not allowed",HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadCredentialsException.class) 
	public ResponseEntity<Object> handleUserNotFoundException(BadCredentialsException ex){
		return new ResponseEntity<>(new ErrorMessage("Incorrect username or password",HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request){
		return new ResponseEntity<>(new ErrorMessage(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
	}
	

	
	
	
}
