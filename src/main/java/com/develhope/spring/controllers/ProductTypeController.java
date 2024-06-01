package com.develhope.spring.controllers;

import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/productTypes")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ProductTypeDto> createProductType(@RequestBody ProductTypeDto productTypeDto) {
        ProductTypeDto newProductType = this.productTypeService.createProductType(productTypeDto);
        return ResponseEntity.created(URI.create("api/v1/productTypes")).body(newProductType);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProductTypeDto>> getAllProductTypes() {
        List<ProductTypeDto> productTypes = this.productTypeService.getAllProductTypes();
        return ResponseEntity.ok().body(productTypes);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductTypeDto> getProductTypeById(@PathVariable String id) {
        ProductTypeDto productTypeFound = this.productTypeService.getSingleProductType(id);
        return ResponseEntity.ok().body(productTypeFound);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductTypeDto> updateProductType(@PathVariable String id, @RequestBody ProductTypeDto productTypeDTO) {
        ProductTypeDto updatedProductType = this.productTypeService.updateProductType(id, productTypeDTO);
        return ResponseEntity.ok().body(updatedProductType);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteProductType(@PathVariable String id) {
        this.productTypeService.deleteProductType(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<?> deleteAllProductTypes() {
        this.productTypeService.deleteAllProductTypes();
        return ResponseEntity.noContent().build();
    }

}
