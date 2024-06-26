package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.CartDto;
import com.develhope.spring.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createController(@RequestBody CartDto cartDto) {
        ResponseModel newCart = this.cartService.createCart(cartDto);
        return ResponseEntity.created(URI.create("api/v1/carts")).body(newCart);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getCartById(@PathVariable String id) {
        ResponseModel cartFound = this.cartService.getCartById(id);
        return ResponseEntity.ok(cartFound);
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAllCarts() {
        ResponseModel cartsList = this.cartService.getAllCarts();
        return ResponseEntity.ok(cartsList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateCartProducts(@PathVariable String id,
                                                            @RequestBody CartDto cartDto) {
        ResponseModel updatedCart = this.cartService.updateCartsCartProducts(id, cartDto);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteCartById(@PathVariable String id) {
        ResponseModel deletedCart = this.cartService.deleteCart(id);
        return ResponseEntity.ok(deletedCart);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAllCarts() {
        ResponseModel deletedCarts = this.cartService.deleteAllCarts();
        return ResponseEntity.ok(deletedCarts);
    }

}
