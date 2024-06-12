package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.OwnerDto;
import com.develhope.spring.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createOwner(@RequestBody OwnerDto ownerDto) {
        ResponseModel response = this.ownerService.addOwner(ownerDto);
        return ResponseEntity.created(URI.create("/owner")).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getOwnerById(@PathVariable String id) {
        ResponseModel response = this.ownerService.getOwnerById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<ResponseModel> getAllOwners() {
        ResponseModel owner = this.ownerService.getAllOwners();
        return ResponseEntity.ok(owner);
    }

    @GetMapping("/emails")
    public ResponseEntity<ResponseModel> getOwnerByEmail(@RequestParam String email) {
        ResponseModel response = this.ownerService.getOwnerByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/deleted")
    public ResponseEntity<ResponseModel> getOwnerByDeletedStatus(@RequestParam Boolean isDeleted) {
        ResponseModel response = this.ownerService.getOwnerByDeletedStatus(isDeleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verified")
    public ResponseEntity<ResponseModel> getOwnersByVerifiedStatus(@RequestParam Boolean isVerified) {
        ResponseModel ownerList = this.ownerService.getOwnersByVerifiedStatus(isVerified);
        return ResponseEntity.ok(ownerList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateOwner(@PathVariable String id, @RequestBody OwnerDto ownerDto) {
        ResponseModel updatedOwner = this.ownerService.updateOwner(id, ownerDto);
        return ResponseEntity.ok(updatedOwner);
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity<ResponseModel> changePassword(@PathVariable String id, @RequestBody OwnerDto ownerToUpdate) {
        ResponseModel response = this.ownerService.updatePassword(id, ownerToUpdate);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteOwnerById(@PathVariable String id) {
        ResponseModel response = this.ownerService.deleteOwner(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAllOwners() {
        ResponseModel response = this.ownerService.deleteAllOwners();
        return ResponseEntity.ok(response);
    }


}
