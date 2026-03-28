package org.example._1_cheav_sarin_pp_spring_homework003.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {
    private String instance;
    private int status;
    private String title;
    private Instant timestamp;
    private Map<String, String> errors;


}