package com.develhope.spring.seeders;

import com.develhope.spring.daos.RestaurantDao;
import com.develhope.spring.daos.RestaurantTypeDao;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.entities.RestaurantEntity;
import com.develhope.spring.models.entities.RestaurantTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantTypeSeederService {
    @Autowired
    RestaurantTypeDao resTypeDao;

    public ResponseModel init(){

        List<RestaurantTypeEntity> restaurantTypeEntityList = new ArrayList<>();

        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("cafeteria")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("fast-food")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("Italian")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("Mexican")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("French")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("Vietnamese")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("Greek")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("Indian")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("Spanish")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("Japanise")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("Chinese")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("Thai")));
        restaurantTypeEntityList.add(resTypeDao.save(new RestaurantTypeEntity("pizzeria")));

        return new ResponseModel(ResponseCode.I, restaurantTypeEntityList);
    }
}
