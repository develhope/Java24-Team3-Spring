package com.develhope.spring.mappers;

import com.develhope.spring.daos.CartDao;
import com.develhope.spring.daos.CustomerDao;
import com.develhope.spring.daos.PaymentDao;
import com.develhope.spring.models.dtos.OrderDto;
import com.develhope.spring.models.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final CustomerDao customerDao;

    private final CartDao cartDao;

    private final PaymentDao paymentDao;

    @Autowired
    public OrderMapper(CustomerDao customerDao, CartDao cartDao, PaymentDao paymentDao) {
        this.customerDao = customerDao;
        this.cartDao = cartDao;
        this.paymentDao = paymentDao;
    }

    public OrderEntity toEntity(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId(orderDto.getId());
        orderEntity.setType(orderDto.getType());
        orderEntity.setStatus(orderDto.getStatus());
        orderEntity.setCreationDate(orderDto.getCreationDate());
        orderEntity.setCustomer(customerDao.findById(orderDto.getCustomerId()).get());
        orderEntity.setCart(cartDao.findById(orderDto.getCartId()).get());
        orderEntity.setPayment(paymentDao.findById(orderDto.getPaymentId()).get());

        return orderEntity;
    }

    public OrderDto toDto(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId(orderEntity.getId());
        orderDto.setType(orderEntity.getType());
        orderDto.setStatus(orderEntity.getStatus());
        orderDto.setCreationDate(orderEntity.getCreationDate());
        orderDto.setCustomerId(orderEntity.getCustomer().getId());
        orderDto.setCartId(orderEntity.getCart().getId());
        orderDto.setPaymentId(orderEntity.getPayment().getId());

        return orderDto;
    }

}