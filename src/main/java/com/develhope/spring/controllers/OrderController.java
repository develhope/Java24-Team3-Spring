package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.OrderDto;
import com.develhope.spring.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/Orders")
public class OrderController {

    private final OrderService OrderService;

    @Autowired
    public OrderController(OrderService OrderService) {
        this.OrderService = OrderService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createController(@RequestBody OrderDto OrderDto) {
        ResponseModel newOrder = this.OrderService.addOrder(OrderDto);
        return ResponseEntity.created(URI.create("api/v1/Orders")).body(newOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getOrderById(@PathVariable String id) {
        ResponseModel OrderFound = this.OrderService.getById(id);
        return ResponseEntity.ok(OrderFound);
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAllOrders() {
        ResponseModel OrdersList = this.OrderService.getAll();
        return ResponseEntity.ok(OrdersList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateOrderProducts(@PathVariable String id, @RequestBody OrderDto OrderDto) {
        ResponseModel updatedOrder = this.OrderService.update(id, OrderDto);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteOrderById(@PathVariable String id) {
        ResponseModel deletedOrder = this.OrderService.delete(id);
        return ResponseEntity.ok(deletedOrder);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAllOrders() {
        ResponseModel deletedOrders = this.OrderService.deleteAll();
        return ResponseEntity.ok(deletedOrders);
    }

}