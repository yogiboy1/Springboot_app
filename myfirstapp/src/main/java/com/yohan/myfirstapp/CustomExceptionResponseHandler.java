package com.yohan.myfirstapp;

import java.net.http.HttpHeaders;
import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionResponseHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAll(Exception ex,WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handlenotfound(NotFoundException ex,WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
		public final ResponseEntity<Object> handleAll(DataIntegrityViolationException ex,WebRequest request){
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Account with that Direct Address already exists , Please find your respective email and password and login" , request.getDescription(false));
			return new ResponseEntity<Object>(exceptionResponse,HttpStatus.CONFLICT);
		}
	
	@ExceptionHandler(FileStorageException.class)
	public final ResponseEntity<Object> handleAll(FileStorageException ex,WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.newsString , request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.CONFLICT);
	}
	
	}
	
