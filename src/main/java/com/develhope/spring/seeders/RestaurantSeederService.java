//package com.develhope.spring.seeders;
//
//import com.develhope.spring.daos.AddressDao;
//import com.develhope.spring.daos.RestaurantDao;
//import com.develhope.spring.models.ResponseCode;
//import com.develhope.spring.models.ResponseModel;
//import com.develhope.spring.models.entities.AddressEntity;
//import com.develhope.spring.models.entities.RestaurantEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@Service
//public class RestaurantSeederService {
//
//    @Autowired
//    RestaurantDao resDao;
//
//    @Autowired
//    AddressDao addressDao;
//
//
//
//    public ResponseModel init(){
//
//        AddressEntity addressEntity = addressDao.save(new AddressEntity( null,"Italy", null, "123", "via malfamata", 5d, "piano terra", new {BigDecimal.valueOf(45.0), BigDecimal.valueOf(70)});
//        RestaurantEntity A = resDao.save(new RestaurantEntity(null, null, "Burger King", null, addressEntity, "Solo carne di qualità", true, false, null, null));
//
//        AddressEntity addressEntity2 = addressDao.save(new AddressEntity(null, "Italy", null, "123", "via San Tommaso", 72d, "piano terra", BigDecimal.valueOf(45.0), BigDecimal.valueOf(70)));
//        RestaurantEntity B = resDao.save(new RestaurantEntity(null, null, "Pizza e Ananas", null, addressEntity2, "Solo carne di qualità", true, false, null, null));
//
//        AddressEntity addressEntity3 = addressDao.save(new AddressEntity(null, "Italy", null,"123",  "via Roma", 33d, "piano terra", BigDecimal.valueOf(45.0), BigDecimal.valueOf(70)));
//        RestaurantEntity C = resDao.save(new RestaurantEntity(null, null, "zupperia", null, addressEntity3, "Solo carne di qualità", true, false, null, null));
//
//
//        return new ResponseModel(ResponseCode.I, new ArrayList<>(Arrays.asList(A, B, C)));
//    }
//
//    public ResponseModel clean(){
//
//        resDao.deleteAll();
//        return new ResponseModel(ResponseCode.J);
//    }
//}
