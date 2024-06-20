package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createCustomer(@RequestBody CustomerDto customerDto) {
        ResponseModel newCustomer = this.customerService.addCustomer(customerDto);
        return ResponseEntity.created(URI.create("api/v1/customers")).body(newCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getCustomerById(@PathVariable String id) {
        ResponseModel customerFound = this.customerService.getCustomerById(id);
        return ResponseEntity.ok(customerFound);
    }

    @GetMapping()
    public ResponseEntity<ResponseModel> getAllCustomers() {
        ResponseModel customerList = this.customerService.getAllCustomers();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/emails")
    public ResponseEntity<ResponseModel> getCustomerByEmail(@RequestParam String email) {
        ResponseModel customerFound = this.customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(customerFound);
    }

    @GetMapping("/deleted")
    public ResponseEntity<ResponseModel> getCustomerByDeletedStatus(@RequestParam Boolean isDeleted) {
        ResponseModel customerList = this.customerService.getCustomerByDeletedStatus(isDeleted);
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/verified")
    public ResponseEntity<ResponseModel> getCustomersByVerifiedStatus(@RequestParam Boolean isVerified) {
        ResponseModel customerList = this.customerService.getCustomersByVerifiedStatus(isVerified);
        return ResponseEntity.ok(customerList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateCustomer(@PathVariable String id, @RequestBody CustomerDto customerDto) {
        ResponseModel updatedCustomer = this.customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity<ResponseModel> changePassword(@PathVariable String id, @RequestBody CustomerDto customerToUpdate) {
        ResponseModel updatedCustomer = this.customerService.updatePassword(id, customerToUpdate);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PatchMapping("/isVerified/{id}")
    public ResponseEntity<ResponseModel> setIsVerified(@PathVariable String id, @RequestParam Boolean isVerified) {
        ResponseModel updateVerifiedStatus = this.customerService.setIsVerified(id, isVerified);
        return ResponseEntity.ok(updateVerifiedStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteCustomerById(@PathVariable String id) {
        ResponseModel deletedCustomer = this.customerService.deleteCustomer(id);
        return ResponseEntity.ok(deletedCustomer);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAllCustomers() {
        ResponseModel deletedCustomers = this.customerService.deleteAllCustomers();
        return ResponseEntity.ok(deletedCustomers);
    }

}
