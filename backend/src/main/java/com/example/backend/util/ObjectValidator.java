package com.example.backend.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.ConstraintViolation;

@Component
public class ObjectValidator {

    @Autowired
    LocalValidatorFactoryBean validator;

    public <T> Map<String, String> getRequestAndSubmitErrors(T t) {
        return getRequestAndSummitErrors(new HashMap<>(), t);
    }

    public <T> Map<String, String> getRequestAndSummitErrors(Map<String, String> errorResult, T t) {
        errorResult.clear();
        Set<ConstraintViolation<T>> violations = validator.getValidator().validate(t);
        for (ConstraintViolation<T> violation : violations) {
            if (violation.getMessage() != null && violation.getMessage().isBlank()) {
                {
                    errorResult.put(violation.getPropertyPath().toString(), violation.getMessage());
                }
            }
        }
        return errorResult;
    }
}
