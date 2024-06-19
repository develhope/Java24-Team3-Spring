package com.develhope.spring.seeders;

import com.develhope.spring.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantTypeSeeder")
public class RestaurantTypeSeederController {
    @Autowired
    private RestaurantTypeSeederService restaurantTypeSeederService;

    @PostMapping
    public ResponseEntity<ResponseModel> initDatabaseData(){
        ResponseModel response  = restaurantTypeSeederService.init();
        return  ResponseEntity.ok().body(response);
    }

}
