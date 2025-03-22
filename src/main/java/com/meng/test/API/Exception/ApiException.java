package com.meng.test.API.Exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@RequiredArgsConstructor
public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String errorMsg; 
}
