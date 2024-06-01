package com.develhope.spring.controllers;

import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.entities.ProductEntity;
import com.develhope.spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto newProduct = this.productService.createProduct(productDto);
        return ResponseEntity.created(URI.create("api/v1/products")).body(newProduct);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = this.productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        ProductDto productFound = this.productService.getSingleProduct(id);
        return ResponseEntity.ok().body(productFound);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = this.productService.updateProduct(id, productDto);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<?> deleteAllProducts() {
        this.productService.deleteAllProducts();
        return ResponseEntity.noContent().build();
    }

}
