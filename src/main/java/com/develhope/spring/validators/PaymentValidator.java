package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidPaymentException;
import com.develhope.spring.models.dtos.PaymentDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidator {

    public void validatePayment(PaymentDto paymentDto) throws InvalidPaymentException {
        if (paymentDto.getPaymentMethod() == null) {
            throw new InvalidPaymentException("Payment method can't be null");
        }

        if (paymentDto.getPaymentStatus() == null) {
            throw new InvalidPaymentException("Payment status can't be null");
        }

        if (paymentDto.getTotalPrice() == null) {
            throw new InvalidPaymentException("Total price can't be null");
        }

    }

}
