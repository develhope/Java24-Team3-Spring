package com.develhope.spring.controllers;

import com.develhope.spring.models.dtos.UserDetailsDto;
import com.develhope.spring.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/userDetails")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsDto> addUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
        UserDetailsDto newUserDetails = this.userDetailsService.addUserDetails(userDetailsDto);
        return ResponseEntity.created(URI.create("api/v1/userDetails")).body(newUserDetails);
    }

    @GetMapping()
    public ResponseEntity<List<UserDetailsDto>> getAllUserDetails() {
        List<UserDetailsDto> userDetailsList = this.userDetailsService.getAllUsersUserDetails();
        return ResponseEntity.ok().body(userDetailsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> getUserDetailsById(@PathVariable Long id) {
        UserDetailsDto userDetailsFound = this.userDetailsService.getUserDetailsById(id);
        return ResponseEntity.ok().body(userDetailsFound);
    }

    @GetMapping("/creationDate")
    public ResponseEntity<List<UserDetailsDto>> getUserDetailsByCreationDate(@RequestParam LocalDate creationDate) {
        List<UserDetailsDto> userDetailsFound = this.userDetailsService.getUserDetailsByCreationDate(creationDate);
        return ResponseEntity.ok().body(userDetailsFound);
    }

    @GetMapping("/phoneNumber")
    public ResponseEntity<List<UserDetailsDto>> getUserDetailsByPhoneNumber(@RequestParam String phoneNumber) {
        List<UserDetailsDto> userDetailsFound = this.userDetailsService.getUserDetailsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok().body(userDetailsFound);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsDto> updateUserDetails(@PathVariable Long id, @RequestBody UserDetailsDto userDetailsUpdates) {
        UserDetailsDto userDetailsToUpdate = this.userDetailsService.updateUserDetails(id, userDetailsUpdates);
        return ResponseEntity.ok().body(userDetailsToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserDetailsById(@PathVariable Long id) {
        this.userDetailsService.deleteUserDetailsById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllUserDetails() {
        this.userDetailsService.deleteAllUserDetails();
        return ResponseEntity.noContent().build();
    }

}
