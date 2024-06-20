package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.PaymentDto;
import com.develhope.spring.models.entities.PaymentMethod;
import com.develhope.spring.models.entities.PaymentStatus;
import com.develhope.spring.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/Payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // POST

    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody PaymentDto paymentDto) {
        ResponseModel newPayment = this.paymentService.create(paymentDto);
        return ResponseEntity.created(URI.create("api/v1/Payments")).body(newPayment);
    }

    // GET

    @GetMapping
    public ResponseEntity<ResponseModel> getAll() {
        ResponseModel payments = this.paymentService.getAll();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/searchById")
    public ResponseEntity<ResponseModel> getById(@RequestParam String id) {
        ResponseModel paymentFound = this.paymentService.getById(id);
        return ResponseEntity.ok(paymentFound);
    }

    @GetMapping("/searchByOrderId")
    public ResponseEntity<ResponseModel> getByOrderId(@RequestParam String orderId) {
        ResponseModel paymentFound = this.paymentService.getByOrderId(orderId);
        return ResponseEntity.ok(paymentFound);
    }

    @GetMapping("/searchByStatus")
    public ResponseEntity<ResponseModel> getByStatus(@RequestParam PaymentStatus status) {
        ResponseModel payments = this.paymentService.getByStatus(status);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/searchByMethod")
    public ResponseEntity<ResponseModel> getByMethod(@RequestParam PaymentMethod method) {
        ResponseModel payments = this.paymentService.getByMethod(method);
        return ResponseEntity.ok(payments);
    }

    // PUT

    @PutMapping("/{id}/paymentStatus")
    public ResponseEntity<ResponseModel> updateStatus(@PathVariable String id, @RequestParam PaymentStatus paymentStatus) {
        ResponseModel updatedPaymentsPaymentStatus = this.paymentService.updateStatus(id, paymentStatus);
        return ResponseEntity.ok(updatedPaymentsPaymentStatus);
    }

    @PutMapping("/{id}/paymentMethod")
    public ResponseEntity<ResponseModel> updateMethod(@PathVariable String id, @RequestParam PaymentMethod paymentMethod) {
        ResponseModel updatedPaymentsPaymentMethod = this.paymentService.updateMethod(id, paymentMethod);
        return ResponseEntity.ok(updatedPaymentsPaymentMethod);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteById(@PathVariable String id) {
        ResponseModel deletedPayment = this.paymentService.deleteById(id);
        return ResponseEntity.ok(deletedPayment);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAll() {
        ResponseModel deletedPayments = this.paymentService.deleteAll();
        return ResponseEntity.ok(deletedPayments);
    }

}
