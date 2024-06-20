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

    private final OrderService OrderService;

    @Autowired
    public OrderController(OrderService OrderService) {
        this.OrderService = OrderService;
    }

    // POST

    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody OrderDto OrderDto) {
        ResponseModel newOrder = this.OrderService.addOrder(OrderDto);
        return ResponseEntity.created(URI.create("api/v1/Orders")).body(newOrder);
    }

    // GET

    @GetMapping
    public ResponseEntity<ResponseModel> getAll() {
        ResponseModel orders = this.OrderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchById")
    public ResponseEntity<ResponseModel> getById(@RequestParam String id) {
        ResponseModel order = this.OrderService.getById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/searchByCustomerId")
    public ResponseEntity<ResponseModel> getByCustomerId(@RequestParam String customerId) {
        ResponseModel orders = this.OrderService.getByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByRestaurantId")
    public ResponseEntity<ResponseModel> getByRestaurantId(@RequestParam String restaurantId) {
        ResponseModel orders = this.OrderService.getByRestaurantId(restaurantId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByPaymentId")
    public ResponseEntity<ResponseModel> getByPaymentId(@RequestParam String paymentId) {
        ResponseModel order = this.OrderService.getByCustomerId(paymentId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/searchByStatus")
    public ResponseEntity<ResponseModel> getByStatus(@RequestParam OrderStatus status) {
        ResponseModel orders = this.OrderService.getByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByType")
    public ResponseEntity<ResponseModel> getByType(@RequestParam OrderType type) {
        ResponseModel orders = this.OrderService.getByType(type);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/createdAfter")
    public ResponseEntity<ResponseModel> getByCreatedAfter(@RequestParam LocalDateTime dateTime) {
        ResponseModel orders = this.OrderService.getByCreationDateAfter(dateTime);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/createdBefore")
    public ResponseEntity<ResponseModel> getByCreatedBefore(@RequestParam LocalDateTime dateTime) {
        ResponseModel orders = this.OrderService.getByCreationDateBefore(dateTime);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/createdBetween")
    public ResponseEntity<ResponseModel> getByCreatedBetween(@RequestParam LocalDateTime dateTime1,
                                                             @RequestParam LocalDateTime dateTime2) {
        ResponseModel orders = this.OrderService.getByCreationDateBetween(dateTime1, dateTime2);
        return ResponseEntity.ok(orders);
    }

    // PUT

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateOrder(@PathVariable String id,
                                                     @RequestBody OrderDto OrderDto) {
        ResponseModel updatedOrder = this.OrderService.update(id, OrderDto);
        return ResponseEntity.ok(updatedOrder);
    }

    // DELETE

    @DeleteMapping("/deleteById")
    public ResponseEntity<ResponseModel> deleteById(@RequestParam String id) {
        ResponseModel deletedOrder = this.OrderService.delete(id);
        return ResponseEntity.ok(deletedOrder);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<ResponseModel> deleteAll() {
        ResponseModel deletedOrders = this.OrderService.deleteAll();
        return ResponseEntity.ok(deletedOrders);
    }

}