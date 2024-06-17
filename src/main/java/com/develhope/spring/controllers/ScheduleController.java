package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ScheduleDto;
import com.develhope.spring.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/schedules")
public class ScheduleController {

    private final ScheduleService service;

    @Autowired
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody ScheduleDto scheduleDto) {
        ResponseModel newSchedule = this.service.create(scheduleDto);
        return ResponseEntity.created(URI.create("api/v1/schedules")).body(newSchedule);
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAll() {
        ResponseModel schedules = this.service.getAll();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getById(@PathVariable String id) {
        ResponseModel scheduleFound = this.service.getById(id);
        return ResponseEntity.ok(scheduleFound);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody ScheduleDto scheduleDto) {
        ResponseModel updatedSchedule = this.service.update(id, scheduleDto);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
        ResponseModel deletedSchedule = this.service.deleteById(id);
        return ResponseEntity.ok(deletedSchedule);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAll() {
        ResponseModel deletedSchedules = this.service.deleteAll();
        return ResponseEntity.ok(deletedSchedules);
    }

}