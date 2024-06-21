package com.develhope.spring.mappers;

import com.develhope.spring.daos.*;
import com.develhope.spring.models.dtos.OrderDto;
import com.develhope.spring.models.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    private final CustomerDao customerDao;
    private final CartDao cartDao;
    private final PaymentDao paymentDao;
    private final RestaurantDao restaurantDao;
    private final ReviewDao reviewDao;

    @Autowired
    public OrderMapper(CustomerDao customerDao, CartDao cartDao, PaymentDao paymentDao, RestaurantDao restaurantDao, ReviewDao reviewDao) {
        this.customerDao = customerDao;
        this.cartDao = cartDao;
        this.paymentDao = paymentDao;
        this.restaurantDao = restaurantDao;
        this.reviewDao = reviewDao;
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
        orderEntity.setRestaurant(restaurantDao.findById(orderDto.getRestaurantId()).get());

        if (orderDto.getPaymentId() != null) {
            orderEntity.setPayment(paymentDao.findById(orderDto.getPaymentId()).get());
        }

        if (orderDto.getReviewId() != null) {
            orderEntity.setReview(reviewDao.findById(orderDto.getReviewId()).get());
        }

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
        orderDto.setRestaurantId(orderEntity.getRestaurant().getId());

        if (orderEntity.getPayment() != null) {
            orderDto.setPaymentId(orderEntity.getPayment().getId());
        }

        if (orderEntity.getReview() != null) {
            orderDto.setReviewId(orderEntity.getReview().getId());
        }

        return orderDto;
    }

    public List<OrderEntity> toEntities(List<OrderDto> orders) {

        if (orders == null || orders.isEmpty()) {
            return null;
        } else {

            List<OrderEntity> mappedOrders = new ArrayList<>();

            for (OrderDto order : orders) {
                mappedOrders.add(this.toEntity(order));
            }

            return mappedOrders;
        }

    }

    public List<OrderDto> toDtos(List<OrderEntity> orders) {

        if (orders == null || orders.isEmpty()) {
            return null;
        } else {

            List<OrderDto> mappedOrders = new ArrayList<>();

            for (OrderEntity order : orders) {
                mappedOrders.add(this.toDto(order));
            }

            return mappedOrders;
        }
    }

}