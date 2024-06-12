package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.UserDetailsDto;
import com.develhope.spring.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/userDetails")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> addUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
        ResponseModel newUserDetails = this.userDetailsService.addUserDetails(userDetailsDto);
        return ResponseEntity.created(URI.create("api/v1/userDetails")).body(newUserDetails);
    }

    @GetMapping()
    public ResponseEntity<ResponseModel> getAllUserDetails() {
        ResponseModel userDetailsList = this.userDetailsService.getAllUsersUserDetails();
        return ResponseEntity.ok(userDetailsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getUserDetailsById(@PathVariable String id) {
        ResponseModel userDetailsFound = this.userDetailsService.getUserDetailsById(id);
        return ResponseEntity.ok(userDetailsFound);
    }

    @GetMapping("/creationDate")
    public ResponseEntity<ResponseModel> getUserDetailsByCreationDate(@RequestParam LocalDate creationDate) {
        ResponseModel userDetailsFound = this.userDetailsService.getUserDetailsByCreationDate(creationDate);
        return ResponseEntity.ok(userDetailsFound);
    }

    @GetMapping("/phoneNumber")
    public ResponseEntity<ResponseModel> getUserDetailsByPhoneNumber(@RequestParam String phoneNumber) {
        ResponseModel userDetailsFound = this.userDetailsService.getUserDetailsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(userDetailsFound);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateUserDetails(@PathVariable String id, @RequestBody UserDetailsDto userDetailsUpdates) {
        ResponseModel userDetailsToUpdate = this.userDetailsService.updateUserDetails(id, userDetailsUpdates);
        return ResponseEntity.ok(userDetailsToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteUserDetailsById(@PathVariable String id) {
        ResponseModel deletedUserDetails = this.userDetailsService.deleteUserDetailsById(id);
        return ResponseEntity.ok(deletedUserDetails);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAllUserDetails() {
        ResponseModel deletedUsersDetails = this.userDetailsService.deleteAllUserDetails();
        return ResponseEntity.ok(deletedUsersDetails);
    }

}
