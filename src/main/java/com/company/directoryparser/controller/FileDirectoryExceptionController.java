package com.company.directoryparser.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.company.directoryparser.exception.DirectoryNotFoundException;

@ControllerAdvice
public class FileDirectoryExceptionController {
	@ExceptionHandler(value = DirectoryNotFoundException.class)
	   public ResponseEntity<Object> exception(DirectoryNotFoundException exception) {
	      return new ResponseEntity<>("Director/File not found", HttpStatus.NOT_FOUND);
	   }

}
