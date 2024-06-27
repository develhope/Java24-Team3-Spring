package com.develhope.spring.controllers;

import com.develhope.spring.exceptions.ExceptionWithResponseCode;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.*;
import com.develhope.spring.models.entities.*;
import com.develhope.spring.services.RestaurantService;
import com.develhope.spring.validators.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private AuthValidator authValidator;

//    @ResponseBody
//    @PostMapping
//    public ResponseEntity<ResponseModel> createRestaurant(@RequestBody RestaurantDto resDto) {
//        ResponseModel responseModel = new ResponseModel();
//        try{
//            RestaurantDto restaurantDto  = restaurantService.createRestaurant(resDto);
//            responseModel.setCodeAndStdMessage(ResponseCode.B)
//                    .setObject(restaurantDto);
//            return ResponseEntity.ok().body(responseModel);
//        } catch (Exception e){
//            responseModel.setCodeAndStdMessage(ResponseCode.A)
//                    .addMessageDetails(e.getMessage());
//            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
//        }
//    }

    @PostMapping
    public ResponseEntity<ResponseModel> createRestaurant(@RequestBody RestaurantDtoCreate resDto) {

        try{
            authValidator.isAdminOrTheOwnerByRetsaurant(resDto);
        } catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseModel(ResponseCode.M).addMessageDetails(e.getMessage()));
        }

        ResponseModel response  = restaurantService.createRestaurant(resDto);
        return  ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAll() {

        try{
            authValidator.isAdmin();
        } catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseModel(ResponseCode.M).addMessageDetails(e.getMessage()));
        }

        ResponseModel response  = restaurantService.getAll();
        return  ResponseEntity.ok().body(response);
    }

    // get allRestaurantByOwnerId
    // get all productByRestaurantId

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getRestaurant(@PathVariable("id") String id) {
        ResponseModel response  = restaurantService.getRestaurantById(id);
        return  ResponseEntity.ok().body(response);
    }

    @GetMapping("/nearAddress")
    public ResponseEntity<ResponseModel> getRestaurantNearAddress(@RequestBody AddressDto addressDto, @RequestParam BigDecimal radious){
        ResponseModel response  = restaurantService.getRestaurantWithinRadious(addressDto, radious);;
        return  ResponseEntity.ok().body(response);
    }

    @ResponseBody
    @GetMapping("/byType")
    public ResponseEntity<ResponseModel> getRestaurantByType(@RequestParam(value="restaurantType") List<String> restaurantTypeStrings) {
        ResponseModel response  = restaurantService.getRestaurantByType(restaurantTypeStrings);
        return  ResponseEntity.ok().body(response);
    }

    @ResponseBody
    @PatchMapping("{id}")
    public ResponseEntity<ResponseModel> updateRestaurant(@PathVariable("id") String id, @RequestBody RestaurantDto resDto) {
        ResponseModel response  = restaurantService.updateRestaurant(id, resDto);
        return  ResponseEntity.ok().body(response);
    }

    @ResponseBody
    @GetMapping("/deliveryOrTakeAway")
    public ResponseEntity<ResponseModel> getRestaurantByDeliveryOrTakeAway(@RequestParam(required = false)  boolean delivery,
                                                                           @RequestParam(required = false)  boolean takeAway) {
        ResponseModel response  = restaurantService.getRestaurantByDeliveryOrTakeAway(delivery, takeAway);
        return ResponseEntity.ok().body(response);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteRestaurant(@PathVariable("id") String id) {
        ResponseModel response  = restaurantService.deleteRestaurantById(id);
        return ResponseEntity.ok().body(response);
    }








}
