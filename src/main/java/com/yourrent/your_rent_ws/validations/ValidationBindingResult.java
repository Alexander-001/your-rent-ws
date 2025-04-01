package com.yourrent.your_rent_ws.validations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ValidationBindingResult {

    public ResponseEntity<Map<String, Object>> validation(BindingResult result, String keyValue, Object value) {
        Map<String, Object> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        errors.put(keyValue, value);
        return ResponseEntity.badRequest().body(errors);
    }
}
