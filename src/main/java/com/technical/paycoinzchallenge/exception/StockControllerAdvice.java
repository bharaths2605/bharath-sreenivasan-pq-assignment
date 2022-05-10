package com.technical.paycoinzchallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class StockControllerAdvice {

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<?> invalidFormatException(InvalidFormatException ex) {

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<Object> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Provide the parameters correctly");
	}

	@ExceptionHandler({ NumberFormatException.class })
	public ResponseEntity<?> numberFormatException(NumberFormatException e) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
	}
	
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<?> notValidInput(MethodArgumentNotValidException e) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Name and current value cannot be empty");
	}
	
	

}
