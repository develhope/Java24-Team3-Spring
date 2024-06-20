package com.develhope.spring.mappers;

import com.develhope.spring.daos.CartDao;
import com.develhope.spring.daos.CustomerDao;
import com.develhope.spring.daos.PaymentDao;
import com.develhope.spring.daos.RestaurantDao;
import com.develhope.spring.models.dtos.OrderDto;
import com.develhope.spring.models.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final CustomerDao customerDao;
    private final CartDao cartDao;
    private final PaymentDao paymentDao;
    private final RestaurantDao restaurantDao;

    @Autowired
    public OrderMapper(CustomerDao customerDao, CartDao cartDao, PaymentDao paymentDao, RestaurantDao restaurantDao) {
        this.customerDao = customerDao;
        this.cartDao = cartDao;
        this.paymentDao = paymentDao;
        this.restaurantDao = restaurantDao;
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
        if (orderDto.getPaymentId() != null) {
            orderEntity.setPayment(paymentDao.findById(orderDto.getPaymentId()).get());
        }
        orderEntity.setRestaurant(restaurantDao.findById(orderDto.getRestaurantId()).get());

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
        if (orderEntity.getPayment() != null) {
            orderDto.setPaymentId(orderEntity.getPayment().getId());
        }
        orderDto.setRestaurantId(orderEntity.getRestaurant().getId());

        return orderDto;
    }

}