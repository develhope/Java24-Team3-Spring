package com.develhope.spring.controllers;

import com.develhope.spring.dtos.CustomerDTO;
import com.develhope.spring.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO newCustomer = this.customerService.addCustomer(customerDTO);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);

    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customerList = this.customerService.getAllCustomers();
        return ResponseEntity.ok().body(customerList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        CustomerDTO customerFound = this.customerService.getCustomer(id);
        return ResponseEntity.ok().body(customerFound);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = this.customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok().body(updatedCustomer);
    }

    @PatchMapping("/password/{id}")
    @ResponseBody
    public ResponseEntity<CustomerDTO> changePassword(@PathVariable Long id, @RequestBody CustomerDTO customerToUpdate) {
        CustomerDTO updatedCustomer = this.customerService.updatePassword(id, customerToUpdate);
        return ResponseEntity.ok().body(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> deleteAllCustomers() {
        this.customerService.deleteAllCustomers();
        return ResponseEntity.noContent().build();
    }

}
