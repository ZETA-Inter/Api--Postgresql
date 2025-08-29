package com.example.Api_Postgresql.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handler(EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(EntityAlreadyExists.class)
    public ResponseEntity<String> handler(EntityAlreadyExists ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handler(RuntimeException ex) {
        Throwable root = ex;
        while (root.getCause() != null) {
            root = root.getCause();
        }

        // Captura violações do banco
        if (root instanceof org.hibernate.exception.ConstraintViolationException) {
            String constraint = ((org.hibernate.exception.ConstraintViolationException) root).getConstraintName();
            String msg = "Violação de integridade";
            if (constraint != null) {
                msg += " na constraint '" + constraint + "'";
            }
            return ResponseEntity.status(409).body(msg);
        }

        // Caso não seja violação de constraint, retorna 500
        return ResponseEntity.status(500).body("Erro inesperado: " + ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handler(DataIntegrityViolationException ex) {
        return ResponseEntity.status(409).body(ex.getMessage() + ", cause: " + ex.getCause());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handler(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            erros.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handler(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handler(NullPointerException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(MultipleValidationException.class)
    public ResponseEntity<Map<String, String>> handleMultipleValidation(MultipleValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getErrors());
    }

}
