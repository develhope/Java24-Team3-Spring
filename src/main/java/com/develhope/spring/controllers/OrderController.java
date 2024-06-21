package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.OrderDto;
import com.develhope.spring.services.OrderService;
import com.develhope.spring.utils.OrderStatus;
import com.develhope.spring.utils.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/Orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService OrderService) {
        this.orderService = OrderService;
    }

    // POST

    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody OrderDto OrderDto) {
        ResponseModel newOrder = this.orderService.addOrder(OrderDto);
        return ResponseEntity.created(URI.create("api/v1/Orders")).body(newOrder);
    }

    // GET

    @GetMapping
    public ResponseEntity<ResponseModel> getAll() {
        ResponseModel orders = this.orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchById")
    public ResponseEntity<ResponseModel> getById(@RequestParam String id) {
        ResponseModel order = this.orderService.getById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/searchByCustomerId")
    public ResponseEntity<ResponseModel> getByCustomerId(@RequestParam String customerId) {
        ResponseModel orders = this.orderService.getByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByRestaurantId")
    public ResponseEntity<ResponseModel> getByRestaurantId(@RequestParam String restaurantId) {
        ResponseModel orders = this.orderService.getByRestaurantId(restaurantId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByPaymentId")
    public ResponseEntity<ResponseModel> getByPaymentId(@RequestParam String paymentId) {
        ResponseModel order = this.orderService.getByCustomerId(paymentId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/searchByStatus")
    public ResponseEntity<ResponseModel> getByStatus(@RequestParam OrderStatus status) {
        ResponseModel orders = this.orderService.getByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByType")
    public ResponseEntity<ResponseModel> getByType(@RequestParam OrderType type) {
        ResponseModel orders = this.orderService.getByType(type);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/createdAfter")
    public ResponseEntity<ResponseModel> getByCreatedAfter(@RequestParam LocalDateTime dateTime) {
        ResponseModel orders = this.orderService.getByCreationDateAfter(dateTime);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/createdBefore")
    public ResponseEntity<ResponseModel> getByCreatedBefore(@RequestParam LocalDateTime dateTime) {
        ResponseModel orders = this.orderService.getByCreationDateBefore(dateTime);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/createdBetween")
    public ResponseEntity<ResponseModel> getByCreatedBetween(@RequestParam LocalDateTime dateTime1,
                                                             @RequestParam LocalDateTime dateTime2) {
        ResponseModel orders = this.orderService.getByCreationDateBetween(dateTime1, dateTime2);
        return ResponseEntity.ok(orders);
    }

    // PUT & PATCH

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable String id,
                                                @RequestBody OrderDto OrderDto) {
        ResponseModel updatedOrder = this.orderService.update(id, OrderDto);
        return ResponseEntity.ok(updatedOrder);
    }

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<ResponseModel> update(@PathVariable String id,
                                                @PathVariable OrderStatus status) {
        ResponseModel updatedOrder = this.orderService.updateStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    // DELETE

    @DeleteMapping("/deleteById")
    public ResponseEntity<ResponseModel> deleteById(@RequestParam String id) {
        ResponseModel deletedOrder = this.orderService.deleteById(id);
        return ResponseEntity.ok(deletedOrder);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAll() {
        ResponseModel deletedOrders = this.orderService.deleteAll();
        return ResponseEntity.ok(deletedOrders);
    }

}