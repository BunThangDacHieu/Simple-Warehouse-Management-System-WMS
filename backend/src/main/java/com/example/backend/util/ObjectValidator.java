package com.example.backend.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.ConstraintViolation;

@Component
public class ObjectValidator {

    @Autowired
    LocalValidatorFactoryBean validator;

    public <T> Map<String, String> getRequestAndSummitErrors(T t) {
        Set<ConstraintViolation<T>> violations = validator.getValidator().validate(t);
        return violations.stream()
                .filter(violation -> StringUtils.isNoneEmpty(violation.getMessage()))
                .collect(
                        Collectors.toMap(violation -> violation.getPropertyPath().toString(),
                                ConstraintViolation::getMessage,
                                (existing, replacement) -> existing
                        )
                );
    }
}
