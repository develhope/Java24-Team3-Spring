package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createProduct(@RequestBody ProductDto productDto) {
        ResponseModel newProduct = this.productService.createProduct(productDto);
        return ResponseEntity.created(URI.create("api/v1/products")).body(newProduct);
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAllProducts() {
        ResponseModel products = this.productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getProductById(@PathVariable String id) {
        ResponseModel productFound = this.productService.getSingleProductById(id);
        return ResponseEntity.ok(productFound);
    }

    @GetMapping("/name")
    public ResponseEntity<ResponseModel> getProductsByName(@RequestParam String name) {
        ResponseModel productsFoundList = this.productService.getProductByName(name);
        return ResponseEntity.ok(productsFoundList);
    }

    @GetMapping("/price")
    public ResponseEntity<ResponseModel> getProductsByPriceRange(@RequestParam BigDecimal upperBoundPrice, @RequestParam BigDecimal lowerBoundPrice) {
        ResponseModel productsFoundList = this.productService.getProductsByPriceRange(upperBoundPrice, lowerBoundPrice);
        return ResponseEntity.ok(productsFoundList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        ResponseModel updatedProduct = this.productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteProduct(@PathVariable String id) {
        ResponseModel deletedProduct = this.productService.deleteProductById(id);
        return ResponseEntity.ok(deletedProduct);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAllProducts() {
        ResponseModel deletedProducts = this.productService.deleteAllProducts();
        return ResponseEntity.ok(deletedProducts);
    }

}
