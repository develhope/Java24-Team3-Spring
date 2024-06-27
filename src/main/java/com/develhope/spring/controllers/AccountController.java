package com.develhope.spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

public class AccountController {
//    @GetMapping("/me")
//    public ResponseEntity<UserEntity> authenticatedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
//
//        return ResponseEntity.ok(currentUser);
//    }
}
