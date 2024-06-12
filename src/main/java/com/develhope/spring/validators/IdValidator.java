package com.develhope.spring.validators;

import com.develhope.spring.exceptions.IdException;
import org.springframework.stereotype.Component;

@Component
public class IdValidator {
    public void noId (String id) throws IdException {
        if (id != null) {
            throw new IdException("Is not possible to insert id's key-value in the body.");
        }
    }

}
