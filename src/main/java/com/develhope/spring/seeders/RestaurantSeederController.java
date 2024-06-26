//package com.develhope.spring.seeders;
//
//import com.develhope.spring.models.ResponseModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/restaurantSeeder")
//public class RestaurantSeederController {
//
//
//
//    @Autowired
//    private RestaurantSeederService restaurantSeederService;
//
//    @PostMapping
//    public ResponseEntity<ResponseModel> initDatabaseData(){
//        ResponseModel response  = restaurantSeederService.init();
//        return  ResponseEntity.ok().body(response);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<ResponseModel> cleanDatabase(){
//        ResponseModel response  = restaurantSeederService.clean();
//        return  ResponseEntity.ok().body(response);
//    }
//}
