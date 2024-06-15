package com.develhope.spring.controllers;

import com.develhope.spring.daos.RestaurantDao;
import com.develhope.spring.daos.RestaurantTypeDao;
import com.develhope.spring.mappers.RestaurantTypeMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.dtos.RestaurantTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantType")
public class RestauratTypeController {

    @Autowired
    RestaurantTypeDao restaurantTypeDao;

    @Autowired
    RestaurantTypeMapper restaurantTypeMapper;

    @ResponseBody
    @PostMapping
    public ResponseEntity<ResponseModel> createRestaurant(@RequestBody List<RestaurantTypeDto> resTypeDto) {
        ResponseModel responseModel = new ResponseModel();
        try{
            List<RestaurantTypeDto> restaurantTypeDtos  = restaurantTypeMapper.toDto(restaurantTypeDao.saveAll(restaurantTypeMapper.toEntity(resTypeDto)));
            responseModel.setCodeAndStdMessage(ResponseCode.B)
                    .setObject(restaurantTypeDtos);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
        } catch (Exception e){
            responseModel.setCodeAndStdMessage(ResponseCode.A)
                    .addMessageDetails(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        }
    }
}
