package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.RiderDto;
import com.develhope.spring.services.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/Riders")
public class RiderController {

    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    // POST

    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody RiderDto riderDto) {
        ResponseModel newRider = this.riderService.createRider(riderDto);
        return ResponseEntity.created(URI.create("api/v1/Riders")).body(newRider);
    }

    // GET

    @GetMapping()
    public ResponseEntity<ResponseModel> getAll() {
        ResponseModel riders = this.riderService.getAll();
        return ResponseEntity.ok(riders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getById(@PathVariable String id) {
        ResponseModel rider = this.riderService.getById(id);
        return ResponseEntity.ok(rider);
    }

    @GetMapping("/Email/{email}")
    public ResponseEntity<ResponseModel> getByEmail(@PathVariable String email) {
        ResponseModel rider = this.riderService.getByEmail(email);
        return ResponseEntity.ok(rider);
    }

    @GetMapping("/Deleted={deleted}")
    public ResponseEntity<ResponseModel> getByDeletedStatus(@PathVariable Boolean deleted) {
        ResponseModel riders = this.riderService.getByDeletedStatus(deleted);
        return ResponseEntity.ok(riders);
    }

    @GetMapping("/Verified={verified}")
    public ResponseEntity<ResponseModel> getByVerifiedStatus(@PathVariable Boolean verified) {
        ResponseModel riders = this.riderService.getByVerifiedStatus(verified);
        return ResponseEntity.ok(riders);
    }

    @GetMapping("/Available={available}")
    public ResponseEntity<ResponseModel> getByAvailableStatus(@PathVariable Boolean available) {
        ResponseModel riders = this.riderService.getByAvailableStatus(available);
        return ResponseEntity.ok(riders);
    }

    // PUT

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable String id,
                                                @RequestBody RiderDto riderDto) {
        ResponseModel rider = this.riderService.updateDetails(id, riderDto);
        return ResponseEntity.ok(rider);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
        ResponseModel rider = this.riderService.deleteById(id);
        return ResponseEntity.ok(rider);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAll() {
        ResponseModel deletedRiders = this.riderService.deleteAll();
        return ResponseEntity.ok(deletedRiders);
    }

}