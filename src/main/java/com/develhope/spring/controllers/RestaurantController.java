package com.develhope.spring.controllers;

import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {



    @Autowired
    private RestaurantService restaurantService;

    @ResponseBody
    @PostMapping
    public ResponseEntity<ResponseModel> createRestaurant(@RequestBody RestaurantDto resDto) {

        ResponseModel response  = restaurantService.createRestaurant(resDto);
        return  ResponseEntity.ok().body(response);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getRestaurant(@PathVariable("id") String id) {
        ResponseModel response  = restaurantService.getRestaurantById(id);
        return  ResponseEntity.ok().body(response);
    }

    @ResponseBody
    @PatchMapping("{id}")
    public ResponseEntity<ResponseModel> updateRestaurant(@PathVariable("id") String id, @RequestBody RestaurantDto resDto) {
        ResponseModel response  = restaurantService.updateRestaurant(id, resDto);
        return  ResponseEntity.ok().body(response);
    }

    @ResponseBody
    @GetMapping
    public ResponseEntity<ResponseModel> getRestaurantByDeliveryOrTakeAway(@RequestParam(required = false)  boolean delivery, @RequestParam(required = false)  boolean takeAway) {
        ResponseModel response  = restaurantService.getRestaurantByDeliveryOrTakeAway(delivery, takeAway);
        return ResponseEntity.ok().body(response);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteRestaurant(@PathVariable("id") String id) {
        ResponseModel response  = restaurantService.deleteRestaurantById(id);
        return ResponseEntity.ok().body(response);
    }


//    @GetMapping
//    public ResponseEntity<List<RestaurantDto>> getRestaurantNearAddress(@RequestBody AddressDto addressDto){
//
//        List<RestaurantEntity>  rest = restaurantService.viewNearByRestaurant(address);
//
//        return new ResponseEntity<>(rest,HttpStatus.OK);
//    }
//




}
