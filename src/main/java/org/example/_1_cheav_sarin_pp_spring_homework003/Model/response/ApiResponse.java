package org.example._1_cheav_sarin_pp_spring_homework003.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ApiResponse<T> {
    private LocalDateTime timestamp ;
    private String message;
        private  String status;
        private  T payload;
    }
