package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.RiderDto;
import com.develhope.spring.services.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/riders")
public class RiderController {

    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createRider(@RequestBody RiderDto riderDto) {
        ResponseModel newRider = this.riderService.addRider(riderDto);
        return ResponseEntity.created(URI.create("api/v1/riders")).body(newRider);
    }

    // GET

    @GetMapping()
    public ResponseEntity<ResponseModel> getAll() {
        ResponseModel riders = this.riderService.getAll();
        return ResponseEntity.ok(riders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getRiderById(@PathVariable String id) {
        ResponseModel rider = this.riderService.getById(id);
        return ResponseEntity.ok(rider);
    }

    @GetMapping("/emails")
    public ResponseEntity<ResponseModel> getByEmail(@RequestParam String email) {
        ResponseModel rider = this.riderService.getByEmail(email);
        return ResponseEntity.ok(rider);
    }

    @GetMapping("/deleted")
    public ResponseEntity<ResponseModel> getByDeletedStatus(@RequestParam Boolean isDeleted) {
        ResponseModel riders = this.riderService.getByDeletedStatus(isDeleted);
        return ResponseEntity.ok(riders);
    }

    @GetMapping("/verified")
    public ResponseEntity<ResponseModel> getByVerifiedStatus(@RequestParam Boolean isVerified) {
        ResponseModel riders = this.riderService.getByVerifiedStatus(isVerified);
        return ResponseEntity.ok(riders);
    }

    @GetMapping("/available")
    public ResponseEntity<ResponseModel> getByAvailableStatus(@RequestParam Boolean isAvailable) {
        ResponseModel riders = this.riderService.getByVerifiedStatus(isAvailable);
        return ResponseEntity.ok(riders);
    }

    // PUT

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody RiderDto riderDto) {
        ResponseModel rider = this.riderService.update(id, riderDto);
        return ResponseEntity.ok(rider);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
        ResponseModel rider = this.riderService.delete(id);
        return ResponseEntity.ok(rider);
    }

}