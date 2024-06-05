package com.develhope.spring.controllers;
import com.develhope.spring.dto.AdminDTO;
import com.develhope.spring.exceptions.AdminNotFoundException;
import com.develhope.spring.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO){
        AdminDTO adminCreated = this.adminService.createAdmin(adminDTO);
        return ResponseEntity.ok(adminCreated);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<AdminDTO>> getAllAdmins(){
        List<AdminDTO> adminList = this.adminService.getAllAdmins();
        return ResponseEntity.ok().body(adminList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable Long id) throws Exception{
        try {
            AdminDTO adminFound = this.adminService.getAdmin(id);
            return ResponseEntity.ok().body(adminFound);
        } catch(AdminNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDTO){
        AdminDTO updatedAdmin = this.adminService.updateAdmin(id, adminDTO);
        return ResponseEntity.ok().body(updatedAdmin);
    }

    @PatchMapping("/password/{id}")
    @ResponseBody
    public ResponseEntity<AdminDTO> updatePassword(@PathVariable Long id, @RequestBody AdminDTO adminDTO){
        AdminDTO updatedAdmin = this.adminService.updatePassword(id, adminDTO);
        return ResponseEntity.ok().body(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id){
        this.adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }


}
