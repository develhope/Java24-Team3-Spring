package com.develhope.spring.mappers;

import com.develhope.spring.daos.OrderDao;
import com.develhope.spring.models.dtos.PaymentDto;
import com.develhope.spring.models.entities.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    private final OrderDao orderDao;

    @Autowired
    public PaymentMapper(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public PaymentEntity toEntity(PaymentDto paymentDto) {
        if (paymentDto == null) {
            return null;
        }

        PaymentEntity paymentEntity = new PaymentEntity();

        paymentEntity.setId(paymentDto.getId());
        paymentEntity.setMethod(paymentDto.getMethod());
        paymentEntity.setStatus(paymentDto.getStatus());
        paymentEntity.setTotalPrice(paymentDto.getTotalPrice());
        paymentEntity.setOrder(orderDao.findById(paymentDto.getOrderId()).get());

        return paymentEntity;
    }

    public PaymentDto toDto(PaymentEntity paymentEntity) {
        if (paymentEntity == null) {
            return null;
        }

        PaymentDto paymentDto = new PaymentDto();

        paymentDto.setId(paymentEntity.getId());
        paymentDto.setMethod(paymentEntity.getMethod());
        paymentDto.setStatus(paymentEntity.getStatus());
        paymentDto.setTotalPrice(paymentEntity.getTotalPrice());
        paymentDto.setOrderId(paymentEntity.getOrder().getId());

        return paymentDto;
    }

}
