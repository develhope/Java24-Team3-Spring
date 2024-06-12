package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.CartProductDto;
import com.develhope.spring.services.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/cartProducts")
public class CartProductController {

    private final CartProductService cartProductService;

    @Autowired
    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createCartProduct(@RequestBody CartProductDto cartProductDto) {
        ResponseModel newCartProduct = this.cartProductService.createCartProduct(cartProductDto);
        return ResponseEntity.created(URI.create("api/v1/cartProducts")).body(newCartProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getCartProductById(@PathVariable String id) {
        ResponseModel cartProductFound = this.cartProductService.getCartProductById(id);
        return ResponseEntity.ok(cartProductFound);
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAllCartProducts() {
        ResponseModel cartProductsList = this.cartProductService.getAllCartProducts();
        return ResponseEntity.ok(cartProductsList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateCartProducts(@PathVariable String id, @RequestBody CartProductDto cartProductDto) {
        ResponseModel updatedCartProduct = this.cartProductService.updateCartProduct(id, cartProductDto);
        return ResponseEntity.ok(updatedCartProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteCartProductById(@PathVariable String id) {
        ResponseModel deletedCartProduct = this.cartProductService.deleteCartProduct(id);
        return ResponseEntity.ok(deletedCartProduct);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAllCartProducts() {
        ResponseModel deletedAllCartProducts = this.cartProductService.deleteAllCartProducts();
        return ResponseEntity.ok(deletedAllCartProducts);
    }

}
