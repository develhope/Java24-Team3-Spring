package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.services.CustomerService;
import com.develhope.spring.validators.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.net.URI;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final AuthValidator authValidator;

    public CustomerController(CustomerService customerService, AuthValidator authValidator) {
        this.customerService = customerService;
        this.authValidator = authValidator;
    }



    @PostMapping
    public ResponseEntity<ResponseModel> createCustomer(@RequestBody CustomerDto customerDto) {

//        try{
//            authValidator.isAdmin();
//        } catch(AuthenticationException e){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseModel(ResponseCode.M).addMessageDetails(e.getMessage()));
//        }

        ResponseModel newCustomer = this.customerService.addCustomer(customerDto);
        return ResponseEntity.created(URI.create("api/v1/customers")).body(newCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getCustomerById(@PathVariable String id) {

//        try{
//            authValidator.isItselfOrAdminById(id);
//        } catch(AuthenticationException e){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseModel(ResponseCode.M).addMessageDetails(e.getMessage()));
//        }

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
