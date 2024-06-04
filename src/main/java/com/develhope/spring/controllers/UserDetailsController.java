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
//
//    @GetMapping()
//    public ResponseEntity<List<UserDetailsDto>> getAllUserDetails() {
//        List<UserDetailsDto> userDetailsList = this.userDetailsService.getAllUsersUserDetails();
//        return ResponseEntity.ok().body(userDetailsList);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDetailsDto> getUserDetailsById(@PathVariable Long id) {
//        UserDetailsDto userDetailsFound = this.userDetailsService.getUserDetailsById(id);
//        return ResponseEntity.ok().body(userDetailsFound);
//    }

    @GetMapping("/creationDate")
    public ResponseEntity<ResponseModel> getUserDetailsByCreationDate(@RequestParam LocalDate creationDate) {
        ResponseModel userDetailsFound = this.userDetailsService.getUserDetailsByCreationDate(creationDate);
        return ResponseEntity.ok().body(userDetailsFound);
    }

    @GetMapping("/phoneNumber")
    public ResponseEntity<ResponseModel> getUserDetailsByPhoneNumber(@RequestParam String phoneNumber) {
        ResponseModel userDetailsFound = this.userDetailsService.getUserDetailsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok().body(userDetailsFound);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateUserDetails(@PathVariable Long id, @RequestBody UserDetailsDto userDetailsUpdates) {
        ResponseModel userDetailsToUpdate = this.userDetailsService.updateUserDetails(id, userDetailsUpdates);
        return ResponseEntity.ok().body(userDetailsToUpdate);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUserDetailsById(@PathVariable Long id) {
//        this.userDetailsService.deleteUserDetailsById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping
//    public ResponseEntity<?> deleteAllUserDetails() {
//        this.userDetailsService.deleteAllUserDetails();
//        return ResponseEntity.noContent().build();
//    }

}
