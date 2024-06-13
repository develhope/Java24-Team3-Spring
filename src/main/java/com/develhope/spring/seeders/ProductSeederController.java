package com.develhope.spring.seeders;

import com.develhope.spring.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productSeeder")
public class ProductSeederController {

    @Autowired
    private ProductSeederService productSeederService;

    @PostMapping
    public ResponseEntity<ResponseModel> initDatabaseData() {
        ResponseModel response = productSeederService.testProducts();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> cleanDatabase() {
        ResponseModel response = productSeederService.cleanDB();
        return ResponseEntity.ok(response);
    }

}
