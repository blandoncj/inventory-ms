package com.blandev.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleGenreNotFoundException(ResourceNotFoundException e, WebRequest req) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<String> handleConflictException(ConflictException e, WebRequest req) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

  @ExceptionHandler(InvalidOperationException.class)
  public ResponseEntity<String> handleInvalidOperationException(InvalidOperationException e, WebRequest req) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler(InsufficientStockException.class)
  public ResponseEntity<String> handleInsufficientStockException(InsufficientStockException e, WebRequest req) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

}
