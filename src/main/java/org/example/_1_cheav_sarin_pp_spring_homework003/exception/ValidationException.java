package org.example._1_cheav_sarin_pp_spring_homework003.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

public class ValidationException extends RuntimeException {

    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}