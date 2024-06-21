package com.develhope.spring.controllers;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.AdminDto;
import com.develhope.spring.exceptions.AdminNotFoundException;
import com.develhope.spring.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ResponseModel> createAdmin(@RequestBody AdminDto adminDTO){
        ResponseModel response = adminService.createAdmin(adminDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<ResponseModel> getAllAdmins(){
        ResponseModel adminList = this.adminService.getAllAdmins();
        return ResponseEntity.ok().body(adminList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseModel> getAdmin(@PathVariable String id) {
        ResponseModel response = adminService.getAdmin(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseModel> updateAdmin(@PathVariable String id, @RequestBody AdminDto adminDTO){
        ResponseModel updatedAdmin = this.adminService.updateAdmin(id, adminDTO);
        return ResponseEntity.ok().body(updatedAdmin);
    }

    @PatchMapping("/password/{id}")
    @ResponseBody
    public ResponseEntity<ResponseModel> updatePassword(@PathVariable String id, @RequestBody AdminDto adminDTO){
        ResponseModel updatedAdmin = this.adminService.updatePassword(id, adminDTO);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseModel> deleteAdminById(@PathVariable String id){
        ResponseModel deletedAdmin = this.adminService.deleteAdmin(id);
        return ResponseEntity.ok(deletedAdmin);
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<ResponseModel> deleteAllAdmins(){
        ResponseModel adminsDeleted = this.adminService.deleteAllAdmins();
        return ResponseEntity.ok(adminsDeleted);
    }


}
