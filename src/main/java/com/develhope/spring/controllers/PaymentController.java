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
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createPayment(@RequestBody PaymentDto paymentDto) {
        ResponseModel newPayment = this.paymentService.createPayment(paymentDto);
        return ResponseEntity.created(URI.create("api/v1/payments")).body(newPayment);
    }


    @GetMapping
    public ResponseEntity<ResponseModel> getAllPayments() {
        ResponseModel payments = this.paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getPaymentsById(@PathVariable String id) {
        ResponseModel paymentFound = this.paymentService.getPaymentById(id);
        return ResponseEntity.ok(paymentFound);
    }

    //@GetMapping
    //getSinglePaymentByOrderId

    @PutMapping("/{id}/paymentStatus")
    public ResponseEntity<ResponseModel> updatePaymentStatus(@PathVariable String id, @RequestParam PaymentStatus paymentStatus) {
        ResponseModel updatedPaymentsPaymentStatus = this.paymentService.updatePaymentStatus(id, paymentStatus);
        return ResponseEntity.ok(updatedPaymentsPaymentStatus);
    }

    @PutMapping("/{id}/paymentMethod")
    public ResponseEntity<ResponseModel> updatePaymentMethod(@PathVariable String id, @RequestParam PaymentMethod paymentMethod) {
        ResponseModel updatedPaymentsPaymentMethod = this.paymentService.updatePaymentMethod(id, paymentMethod);
        return ResponseEntity.ok(updatedPaymentsPaymentMethod);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deletePayment(@PathVariable String id) {
        ResponseModel deletedPayment = this.paymentService.deletePaymentById(id);
        return ResponseEntity.ok(deletedPayment);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAllPayments() {
        ResponseModel deletedPayments = this.paymentService.deleteAllPayments();
        return ResponseEntity.ok(deletedPayments);
    }

}
