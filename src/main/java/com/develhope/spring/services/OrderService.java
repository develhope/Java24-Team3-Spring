package com.develhope.spring.services;

import com.develhope.spring.daos.OrderDao;
import com.develhope.spring.exceptions.InvalidOrderException;
import com.develhope.spring.mappers.OrderMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.OrderDto;
import com.develhope.spring.models.entities.OrderEntity;
import com.develhope.spring.utils.OrderStatus;
import com.develhope.spring.utils.OrderType;
import com.develhope.spring.validators.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderDao dao;
    private final OrderMapper mapper;
    private final OrderValidator validator;

    @Autowired
    public OrderService(OrderDao dao, OrderMapper mapper, OrderValidator validator) {
        this.dao = dao;
        this.mapper = mapper;
        this.validator = validator;
    }

    // CREATE

    public ResponseModel addOrder(OrderDto orderDto) {
        try {
            validator.validateOrder(orderDto);
            OrderEntity newOrder = this.mapper.toEntity(orderDto);
            this.dao.saveAndFlush(newOrder);
            return new ResponseModel(ResponseCode.B, this.mapper.toDto(newOrder));
        } catch (InvalidOrderException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    // READ

    public ResponseModel getAll() {
        List<OrderDto> orders = this.dao.findAll().stream().map(mapper::toDto).toList();
        if (orders.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No orders found.");
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getById(String id) {
        Optional<OrderEntity> orderFound = this.dao.findById(id);
        if (orderFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Order ID not found.");
        } else {
            return new ResponseModel(ResponseCode.C, this.mapper.toDto(orderFound.get()));
        }
    }

    public ResponseModel getByCustomerId(String customerId) {
        List<OrderEntity> orders = this.dao.findByCustomerId(customerId);
        if (orders.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No orders created by this customer ID found.");
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getByPaymentId(String paymentId) {
        Optional<OrderEntity> orderFound = this.dao.findByPaymentId(paymentId);
        if (orderFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No order associated to this payment found.");
        } else {
            return new ResponseModel(ResponseCode.C, this.mapper.toDto(orderFound.get()));
        }
    }

    public ResponseModel getByRestaurantId(String restaurantId) {
        List<OrderEntity> orders = this.dao.findByRestaurantId(restaurantId);
        if (orders.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No orders associated to this restaurant found.");
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getByStatus(OrderStatus status) {
        List<OrderDto> orders = this.dao.findByStatus(status).stream().map(mapper::toDto).toList();
        if (orders.isEmpty()) {
            String messageDetails = "No " + status.toString() + "orders found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getByType(OrderType type) {
        List<OrderDto> orders = this.dao.findByType(type).stream().map(mapper::toDto).toList();
        if (orders.isEmpty()) {
            String messageDetails = "No " + type.toString() + "orders found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getByCreationDateAfter(LocalDateTime dateTime) {
        List<OrderDto> orders = this.dao.findByCreationDateAfter(dateTime).stream().map(mapper::toDto).toList();
        if (orders.isEmpty()) {
            String messageDetails = "No orders created after " + dateTime.toString() + " found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getByCreationDateBefore(LocalDateTime dateTime) {
        List<OrderDto> orders = this.dao.findByCreationDateBefore(dateTime).stream().map(mapper::toDto).toList();
        if (orders.isEmpty()) {
            String messageDetails = "No orders created before " + dateTime.toString() + " found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getByCreationDateBetween(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        List<OrderDto> orders = this.dao.findByCreationDateBetween(dateTime1, dateTime2).stream().map(mapper::toDto).toList();
        if (orders.isEmpty()) {
            String messageDetails = "No orders created between " + dateTime1.toString() + " and " + dateTime2.toString() + " found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    // UPDATE

    public ResponseModel update(String id, OrderDto updatedOrder) {

        Optional<OrderEntity> orderToUpdate = this.dao.findById(id);

        if (orderToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Order ID not found.");
        } else if (updatedOrder != null) {

            OrderEntity updatedOrderEntity = this.mapper.toEntity(updatedOrder);

            if (updatedOrder.getType() != null) {
                orderToUpdate.get().setType(updatedOrderEntity.getType());
            }

            if (updatedOrder.getStatus() != null) {
                orderToUpdate.get().setStatus(updatedOrderEntity.getStatus());
            }

            if (updatedOrder.getCreationDate() != null) {
                orderToUpdate.get().setCreationDate(updatedOrderEntity.getCreationDate());
            }

            if (updatedOrder.getCartId() != null) {
                orderToUpdate.get().setCart(updatedOrderEntity.getCart());
            }

            return new ResponseModel(ResponseCode.G, this.mapper.toDto(this.dao.saveAndFlush(orderToUpdate.get())));
        }

        return new ResponseModel(ResponseCode.A).addMessageDetails("Update failed (null body).");
    }

    // DELETE

    public ResponseModel delete(String id) {
        if (!this.dao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Order ID not found.");
        } else {
            this.dao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("Order deleted successfully.");
        }
    }

    public ResponseModel deleteAll() {
        this.dao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All orders deleted.");
    }

}