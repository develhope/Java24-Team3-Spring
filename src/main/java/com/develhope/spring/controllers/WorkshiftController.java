package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.WorkshiftDto;
import com.develhope.spring.services.WorkshiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/workshifts")
public class WorkshiftController {

    private final WorkshiftService service;

    @Autowired
    public WorkshiftController(WorkshiftService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody WorkshiftDto workshiftDto) {
        ResponseModel newWorkshift = this.service.create(workshiftDto);
        return ResponseEntity.created(URI.create("api/v1/workshifts")).body(newWorkshift);
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAll() {
        ResponseModel workshifts = this.service.getAll();
        return ResponseEntity.ok(workshifts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getById(@PathVariable String id) {
        ResponseModel workshiftFound = this.service.getById(id);
        return ResponseEntity.ok(workshiftFound);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody WorkshiftDto workshiftDto) {
        ResponseModel updatedWorkshift = this.service.update(id, workshiftDto);
        return ResponseEntity.ok(updatedWorkshift);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
        ResponseModel deletedWorkshift = this.service.deleteById(id);
        return ResponseEntity.ok(deletedWorkshift);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAll() {
        ResponseModel deletedWorkshifts = this.service.deleteAll();
        return ResponseEntity.ok(deletedWorkshifts);
    }

}