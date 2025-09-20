package com.kida.kidastore.exception.handlException;

import com.kida.kidastore.shared.ErrorMessage;
import com.kida.kidastore.exception.ResourceAlreadyExistException;
import com.kida.kidastore.exception.ResourceNotFoundException;
import com.kida.kidastore.shared.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GlobalResponse<?>> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .build();
        List<ErrorMessage> errors = Arrays.asList(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GlobalResponse<>(errors));
    }
    @ExceptionHandler(ResourceAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<GlobalResponse<?>> handleResourceAreadyExist(ResourceAlreadyExistException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .build();
        List<ErrorMessage> errors = Arrays.asList(errorMessage);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GlobalResponse<>(errors));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalResponse<?>> handleValidationError(MethodArgumentNotValidException ex) {
        List<ErrorMessage> errors = ex.getBindingResult()
                .getFieldErrors() // Récupère la liste des FieldError
                .stream() // Convertit en un Stream pour utiliser l'API Stream
                .map(error -> new ErrorMessage(error.getField(), error.getDefaultMessage())) // Mappe chaque FieldError en un nouvel objet ErrorMessage
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GlobalResponse<>(errors));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GlobalResponse<?>> handleGlobalException(Exception ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .build();
        List<ErrorMessage> errors = Arrays.asList(errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GlobalResponse<>(errors));
    }
}