package com.develhope.spring.seeders;

import com.develhope.spring.daos.ProductDao;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.entities.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProductSeederService {
    @Autowired
    ProductDao productDao;

    public ResponseModel testProducts() {
        BigDecimal price = new BigDecimal("10.90");
        ProductEntity productEntity1 = productDao.save(new ProductEntity("Pizza Margherita", price, "base, salsa di pomodoro, mozzarella", null));
        ProductEntity productEntity2 = productDao.save(new ProductEntity("Bistecca di manzo", price, "carne di manzo", null));
        ProductEntity productEntity3 = productDao.save(new ProductEntity("Pasta amatriciana", price, "pasta, sugo di pomodoro, guanciale", null));

        return new ResponseModel(ResponseCode.I, new ArrayList<>(Arrays.asList(productEntity1, productEntity2, productEntity3)));
    }

    public ResponseModel cleanDB() {
        productDao.deleteAll();
        return new ResponseModel(ResponseCode.J);
    }

}
