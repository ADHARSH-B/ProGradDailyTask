package com.ticketbooking.main.ExceptionHandlers;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.ticketbooking.main.dao.ErrorMessage;



@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class) 
	public ResponseEntity<Object> handleUserNotFoundException(DataIntegrityViolationException ex){
		return new ResponseEntity<>(new ErrorMessage(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
	}

	
	
	
}
