
package com.ameer.laundrymanagment.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("status", 400);
        body.put("error", "Bad Request");

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .distinct()
                .collect(Collectors.joining(", "));

        body.put("message", message);
        body.put("path", request.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatus(
            ResponseStatusException ex,
            HttpServletRequest request) {

        HttpStatus status =
                HttpStatus.valueOf(ex.getStatusCode().value());

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getReason());
        body.put("path", request.getRequestURI());

        return ResponseEntity.status(status).body(body);
    }
}

