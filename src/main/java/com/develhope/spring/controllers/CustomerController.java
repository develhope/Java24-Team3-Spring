package com.develhope.spring.controllers;

import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto newCustomer = this.customerService.addCustomer(customerDto);
        return ResponseEntity.created(URI.create("api/v1/customers")).body(newCustomer);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customerList = this.customerService.getAllCustomers();
        return ResponseEntity.ok().body(customerList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        CustomerDto customerFound = this.customerService.getCustomer(id);
        return ResponseEntity.ok().body(customerFound);
    }

    @GetMapping("/emails")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@RequestParam String email){
        CustomerDto customerFound = this.customerService.getCustomerByEmail(email);
        return ResponseEntity.ok().body(customerFound);
    }

    @GetMapping("/deleted")
    public ResponseEntity<List<CustomerDto>> getCustomerByDeletedStatus(@RequestParam Boolean isDeleted) {
        List<CustomerDto> customerList = this.customerService.getCustomerByDeletedStatus(isDeleted);
        return ResponseEntity.ok().body(customerList);
    }

    @GetMapping("/verified")
    public ResponseEntity<List<CustomerDto>> getCustomersByVerifiedStatus(@RequestParam Boolean isVerified) {
        List<CustomerDto> customerList = this.customerService.getCustomersByVerifiedStatus(isVerified);
        return ResponseEntity.ok().body(customerList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = this.customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok().body(updatedCustomer);
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity<CustomerDto> changePassword(@PathVariable Long id, @RequestBody CustomerDto customerToUpdate) {
        CustomerDto updatedCustomer = this.customerService.updatePassword(id, customerToUpdate);
        return ResponseEntity.ok().body(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllCustomers() {
        this.customerService.deleteAllCustomers();
        return ResponseEntity.noContent().build();
    }

}
