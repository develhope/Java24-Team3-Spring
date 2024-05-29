package com.develhope.spring.controllers;

import com.develhope.spring.dtos.ProductDTO;
import com.develhope.spring.entities.ProductEntity;
import com.develhope.spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO newProduct = this.productService.createProduct(productDTO);
        return ResponseEntity.created(URI.create("api/v1/products")).body(newProduct);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        List<ProductEntity> products = this.productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductEntity> getProductById(@PathVariable UUID id) {
        ProductEntity productFound = this.productService.getSingleProduct(id);
        return ResponseEntity.ok().body(productFound);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = this.productService.updateProduct(id, productDTO);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteProduct(@PathVariable UUID id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity deleteAllProducts() {
        this.productService.deleteAllProducts();
        return ResponseEntity.noContent().build();
    }

}
