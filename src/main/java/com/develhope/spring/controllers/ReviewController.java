package com.develhope.spring.controllers;

import com.develhope.spring.models.Rating;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ReviewDto;
import com.develhope.spring.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/Reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // POST

    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody ReviewDto reviewDto){
        ResponseModel response = reviewService.create(reviewDto);
        return ResponseEntity.ok().body(response);
    }

    // GET

    @GetMapping
    public ResponseEntity<ResponseModel> getAll(){
        ResponseModel reviewList = this.reviewService.getAll();
        return ResponseEntity.ok().body(reviewList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getById(@PathVariable String id){
        ResponseModel response = reviewService.getById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/rating/{restaurantId}")
    public ResponseEntity<ResponseModel> getMeanRating(@PathVariable String restaurantId) {
        ResponseModel response = reviewService.getMeanRating(restaurantId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/searchByCustomerId")
    public ResponseEntity<ResponseModel> getByCustomerId(@RequestParam String customerId) {
        ResponseModel orders = this.reviewService.getByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByRestaurantId")
    public ResponseEntity<ResponseModel> getByRestaurantId(@RequestParam String restaurantId) {
        ResponseModel orders = this.reviewService.getByRestaurantId(restaurantId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/createdAfter")
    public ResponseEntity<ResponseModel> getByCreatedAfter(@RequestParam LocalDateTime dateTime) {
        ResponseModel orders = this.reviewService.getByCreationDateAfter(dateTime);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/createdBefore")
    public ResponseEntity<ResponseModel> getByCreatedBefore(@RequestParam LocalDateTime dateTime) {
        ResponseModel orders = this.reviewService.getByCreationDateBefore(dateTime);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/createdBetween")
    public ResponseEntity<ResponseModel> getByCreatedBetween(@RequestParam LocalDateTime dateTime1,
                                                             @RequestParam LocalDateTime dateTime2) {
        ResponseModel orders = this.reviewService.getByCreationDateBetween(dateTime1, dateTime2);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/Ratings/{rating}")
    public ResponseEntity<ResponseModel> getByRating(@PathVariable Rating rating) {
        ResponseModel orders = this.reviewService.getByRating(rating);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/Ratings/lessThanEqual")
    public ResponseEntity<ResponseModel> getByRatingLessThanEqual(@RequestParam Rating rating) {
        ResponseModel orders = this.reviewService.getByRatingLessThanEqual(rating);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/Ratings/greaterThanEqual")
    public ResponseEntity<ResponseModel> getByRatingGreaterThanEqual(@RequestParam Rating rating) {
        ResponseModel orders = this.reviewService.getByRatingGreaterThanEqual(rating);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/Ratings/between")
    public ResponseEntity<ResponseModel> getByRatingBetween(@RequestParam Rating minRating,
                                                             @RequestParam Rating maxRating) {
        ResponseModel orders = this.reviewService.getByRatingBetween(minRating, maxRating);
        return ResponseEntity.ok(orders);
    }

    // PUT

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseModel> updateReview(@PathVariable String id, @RequestBody ReviewDto reviewDto){
        ResponseModel updateReview = this.reviewService.updateReview(id, reviewDto);
        return ResponseEntity.ok().body(updateReview);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteById(@PathVariable String id){
        ResponseModel deletedReview = this.reviewService.deleteById(id);
        return ResponseEntity.ok(deletedReview);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAll(){
        ResponseModel reviewsDeleted = this.reviewService.deleteAll();
        return ResponseEntity.ok(reviewsDeleted);
    }

}
