package com.develhope.spring.controllers;

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

    @PostMapping
    public ResponseEntity<?> createRestaurant(@RequestBody RestaurantDto resDto) {
        RestaurantDto resDtoCreated  = restaurantService.createRestaurant(resDto);
        return new ResponseEntity<RestaurantDto>(resDtoCreated, HttpStatus.OK);
    }

     @PatchMapping("{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable("id") Long id, @RequestBody RestaurantDto resDto) {
        RestaurantDto restDtoUpdated = restaurantService.updatePatchRestaurant(id, resDto);
        if (restDtoUpdated != null) {
            return ResponseEntity.ok().body(restDtoUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurant(@PathVariable("id") Long id) {
        RestaurantDto resDtoGot  = restaurantService.getRestaurant(id);
        return new ResponseEntity<RestaurantDto>(resDtoGot, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getRestaurantNearAddress(@RequestBody AddressDto addressDto){

        List<RestaurantEntity>  rest = restaurantService.viewNearByRestaurant(address);

        return new ResponseEntity<>(rest,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantEntity>> viweRestaurantByItemNameHandler(@RequestParam String itemName) throws RestaurantException{

        List<RestaurantEntity> rest = restaurantService.viweRestaurantByItemName(itemName);

        return new ResponseEntity<>(rest,HttpStatus.OK);
    }



}
