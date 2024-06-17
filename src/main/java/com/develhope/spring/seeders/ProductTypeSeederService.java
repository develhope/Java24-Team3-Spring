package com.develhope.spring.seeders;

import com.develhope.spring.daos.ProductTypeDao;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.entities.ProductTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProductTypeSeederService {
    @Autowired
    ProductTypeDao productTypeDao;

    public ResponseModel injectTestProductTypes(){
        ProductTypeEntity productTypeEntity1 = productTypeDao.save(new ProductTypeEntity("Vegan"));
        ProductTypeEntity productTypeEntity2 = productTypeDao.save(new ProductTypeEntity("Sushi"));
        ProductTypeEntity productTypeEntity3 = productTypeDao.save(new ProductTypeEntity("Pasta"));
        ProductTypeEntity productTypeEntity4 = productTypeDao.save(new ProductTypeEntity("Pizza"));
        ProductTypeEntity productTypeEntity5 = productTypeDao.save(new ProductTypeEntity("Organic"));
        ProductTypeEntity productTypeEntity6 = productTypeDao.save(new ProductTypeEntity("Vegetarian"));
        ProductTypeEntity productTypeEntity7 = productTypeDao.save(new ProductTypeEntity("Lactose-free"));
        ProductTypeEntity productTypeEntity8 = productTypeDao.save(new ProductTypeEntity("No added sugar"));
        ProductTypeEntity productTypeEntity9 = productTypeDao.save(new ProductTypeEntity("Non-GMO"));
        ProductTypeEntity productTypeEntity10 = productTypeDao.save(new ProductTypeEntity("Paleo"));
        ProductTypeEntity productTypeEntity11 = productTypeDao.save(new ProductTypeEntity("Keto"));
        ProductTypeEntity productTypeEntity12 = productTypeDao.save(new ProductTypeEntity("Gluten-free"));
        ProductTypeEntity productTypeEntity13 = productTypeDao.save(new ProductTypeEntity("Dairy-free"));
        ProductTypeEntity productTypeEntity14 = productTypeDao.save(new ProductTypeEntity("Soy-free"));
        ProductTypeEntity productTypeEntity15 = productTypeDao.save(new ProductTypeEntity("Nut-free"));
        ProductTypeEntity productTypeEntity16 = productTypeDao.save(new ProductTypeEntity("Burger"));

        return new ResponseModel(ResponseCode.I, new ArrayList<>(Arrays.asList(productTypeEntity1, productTypeEntity2,
                productTypeEntity3, productTypeEntity4, productTypeEntity5, productTypeEntity6, productTypeEntity7,
                productTypeEntity8, productTypeEntity9, productTypeEntity10, productTypeEntity11, productTypeEntity12,
                productTypeEntity13, productTypeEntity14, productTypeEntity15, productTypeEntity16)));

    }

    public ResponseModel cleanDB() {
        productTypeDao.deleteAll();
        return new ResponseModel(ResponseCode.J);
    }

}
