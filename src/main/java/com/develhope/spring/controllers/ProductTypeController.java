package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/productTypes")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createProductType(@RequestBody ProductTypeDto productTypeDto) {
        ResponseModel newProductType = this.productTypeService.createProductType(productTypeDto);
        return ResponseEntity.created(URI.create("api/v1/productTypes")).body(newProductType);
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAllProductTypes() {
        ResponseModel productTypes = this.productTypeService.getAllProductTypes();
        return ResponseEntity.ok(productTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getProductTypeById(@PathVariable String id) {
        ResponseModel productTypeFound = this.productTypeService.getSingleProductType(id);
        return ResponseEntity.ok(productTypeFound);
    }

    @GetMapping("/productType")
    public ResponseEntity<ResponseModel> getProductTypeByName(@RequestParam String productType) {
        ResponseModel productTypes = this.productTypeService.getProductTypeByName(productType);
        return ResponseEntity.ok(productTypes);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateProductType(@PathVariable String id, @RequestBody ProductTypeDto productTypeUpdates) {
        ResponseModel updatedProductType = this.productTypeService.updateProductType(id, productTypeUpdates);
        return ResponseEntity.ok(updatedProductType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteProductType(@PathVariable String id) {
        ResponseModel deletedProductType = this.productTypeService.deleteProductType(id);
        return ResponseEntity.ok(deletedProductType);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAllProductTypes() {
        ResponseModel deletedProductTypes = this.productTypeService.deleteAllProductTypes();
        return ResponseEntity.ok(deletedProductTypes);
    }

}
