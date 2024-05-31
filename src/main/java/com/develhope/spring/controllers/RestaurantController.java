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
    public ResponseEntity<ResponseModel> getRestaurant(@PathVariable("id") Long id) {
        ResponseModel response  = restaurantService.getRestaurantById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<ResponseModel> getRestaurantByDeliveryOrTakeAway(@RequestParam(required = false)  boolean delivery, @RequestParam(required = false)  boolean takeAway) {
//        ResponseModel response  = restaurantService.getRestaurantByDeliveryOrTakeAway(delivery, takeAway);
//        return new ResponseEntity(response, HttpStatus.OK);
//    }


//    @GetMapping
//    public ResponseEntity<List<RestaurantDto>> getRestaurantNearAddress(@RequestBody AddressDto addressDto){
//
//        List<RestaurantEntity>  rest = restaurantService.viewNearByRestaurant(address);
//
//        return new ResponseEntity<>(rest,HttpStatus.OK);
//    }
//


//     @PatchMapping("{id}")
//    public ResponseEntity<?> updateRestaurant(@PathVariable("id") Long id, @RequestBody RestaurantDto resDto) {
//        RestaurantDto restDtoUpdated = restaurantService.updatePatchRestaurant(id, resDto);
//        if (restDtoUpdated != null) {
//            return ResponseEntity.ok().body(restDtoUpdated);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
