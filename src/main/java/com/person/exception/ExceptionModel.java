package com.person.exception;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionModel {
    private Integer statusCode;
    private String message;
    private LocalDateTime timestamp;
}
