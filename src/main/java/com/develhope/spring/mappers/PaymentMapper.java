package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.PaymentDto;
import com.develhope.spring.models.entities.PaymentEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentEntity toEntity(PaymentDto paymentDto) {
        if (paymentDto == null) {
            return null;
        }

        PaymentEntity paymentEntity = new PaymentEntity();

        paymentEntity.setId(paymentDto.getId());
        paymentEntity.setOrderId(paymentDto.getOrderId());
        paymentEntity.setPaymentMethod(paymentDto.getPaymentMethod());
        paymentEntity.setPaymentStatus(paymentDto.getPaymentStatus());
        paymentEntity.setTotalPrice(paymentDto.getTotalPrice());

        return paymentEntity;
    }

    public PaymentDto toDto(PaymentEntity paymentEntity) {
        if (paymentEntity == null) {
            return null;
        }

        PaymentDto paymentDto = new PaymentDto();

        paymentDto.setId(paymentEntity.getId());
        paymentDto.setOrderId(paymentEntity.getOrderId());
        paymentDto.setPaymentMethod(paymentEntity.getPaymentMethod());
        paymentDto.setPaymentStatus(paymentEntity.getPaymentStatus());
        paymentDto.setTotalPrice(paymentEntity.getTotalPrice());

        return paymentDto;
    }

}
