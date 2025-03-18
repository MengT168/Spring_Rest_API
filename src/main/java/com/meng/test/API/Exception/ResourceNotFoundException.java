package com.meng.test.API.Exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String resourceName, Integer id) {
        super(HttpStatus.NOT_FOUND, "%s with id = %d not found".formatted(resourceName, id));
    }
}
