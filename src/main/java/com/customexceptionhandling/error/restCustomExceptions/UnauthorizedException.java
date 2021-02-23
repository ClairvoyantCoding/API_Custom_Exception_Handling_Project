package com.customexceptionhandling.error.restCustomExceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {
    private final String message;
    private final Throwable cause;
}
