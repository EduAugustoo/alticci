package com.eduardo.alticci.errors;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
