package com.develhope.spring.controllers;

import com.develhope.spring.dtos.ProductTypeDTO;
import com.develhope.spring.entities.ProductTypeEntity;
import com.develhope.spring.services.ProductTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/productTypes")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ProductTypeDTO> createProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        ProductTypeDTO newProductType = this.productTypeService.createProductType(productTypeDTO);
        return new ResponseEntity<>(newProductType, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProductTypeEntity>> getAllProductTypes() {
        List<ProductTypeEntity> productTypes = this.productTypeService.getAllProductTypes();
        return ResponseEntity.ok().body(productTypes);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductTypeEntity> getProductTypeById(@PathVariable UUID id) {
        ProductTypeEntity productTypeFound = this.productTypeService.getSingleProductType(id);
        return ResponseEntity.ok().body(productTypeFound);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductTypeDTO> updateProductType(@PathVariable UUID id, @RequestBody ProductTypeDTO productTypeDTO) {
        ProductTypeDTO updatedProductType = this.productTypeService.updateProductType(id, productTypeDTO);
        return ResponseEntity.ok().body(updatedProductType);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteProductType(@PathVariable UUID id) {
        this.productTypeService.deleteProductType(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity deleteAllProductTypes() {
        this.productTypeService.deleteAllProductTypes();
        return ResponseEntity.noContent().build();
    }

}
