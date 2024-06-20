package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ReviewDto;
import com.develhope.spring.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ResponseModel> createReview(@RequestBody ReviewDto reviewDto){
        ResponseModel response = reviewService.createReview(reviewDto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<ResponseModel> getAllReview(){
        ResponseModel reviewList = this.reviewService.getAllReview();
        return ResponseEntity.ok().body(reviewList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseModel> getReview(@PathVariable String id){
        ResponseModel response = reviewService.getReview(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseModel> updateReview(@PathVariable String id, @RequestBody ReviewDto reviewDto){
        ResponseModel updateReview = this.reviewService.updateReview(id, reviewDto);
        return ResponseEntity.ok().body(updateReview);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseModel> deleteReviewById(@PathVariable String id){
        ResponseModel deletedReview = this.reviewService.deleteReview(id);
        return ResponseEntity.ok(deletedReview);
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<ResponseModel> deleteAllReviews(){
        ResponseModel reviewsDeleted = this.reviewService.deleteAllReview();
        return ResponseEntity.ok(reviewsDeleted);
    }

    @GetMapping("/rating/{restaurantId}")
    @ResponseBody
    public ResponseEntity<ResponseModel> getMeanRating(@PathVariable String restaurantId) {
        ResponseModel response = reviewService.getMeanRating(restaurantId);
        return ResponseEntity.ok().body(response);
    }

}
