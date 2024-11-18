package com.my_bank.mortgage_calculator.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidMortgageTypeException.class)
  public ResponseEntity<Map<String, Object>> handleInvalidMortgageType(InvalidMortgageTypeException ex) {
    Map<String, Object> errorDetails = Map.of(
        "timestamp", LocalDateTime.now(),
        "status", HttpStatus.BAD_REQUEST.value(),
        "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
        "message", ex.getMessage()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
  }

  @ExceptionHandler(InvalidMaturityPeriodException.class)
  public ResponseEntity<Map<String, Object>> handleInvalidMaturityPeriod(InvalidMaturityPeriodException ex) {
    Map<String, Object> errorDetails = Map.of(
        "timestamp", LocalDateTime.now(),
        "status", HttpStatus.BAD_REQUEST.value(),
        "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
        "message", ex.getMessage()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

}
