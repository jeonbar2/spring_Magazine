package com.spring_magazine.common.error;

import lombok.Builder;
import lombok.Getter;

@Getter // Response에 Getter 필수 !!!!!!!!!!
@Builder
public class ErrorResponse {
    private String message;
    private String exceptionType;
}
