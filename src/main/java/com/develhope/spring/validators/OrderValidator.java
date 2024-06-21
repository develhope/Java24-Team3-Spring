package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidOrderException;
import com.develhope.spring.models.dtos.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

    public void validateOrder(OrderDto orderDto) throws InvalidOrderException {
    }

}