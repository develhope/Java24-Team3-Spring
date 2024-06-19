package com.develhope.spring.validators;

import com.develhope.spring.daos.RestaurantDao;
import com.develhope.spring.exceptions.RestaurantNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantValidator {
    @Autowired
    RestaurantDao restaurantDao;
    @Autowired
    RestaurantDao resDao;


    public void validateRestaurantName(String restaurantName) throws RestaurantNameException {
        if (restaurantName == null || restaurantName.isEmpty()) {
            throw new RestaurantNameException("The restaurant name cannot be empty.");
        }

        // "^(?!\\d+$)[A-Za-z0-9 & ']+$"
        if (!restaurantName.matches("^(?!\\d+$)[A-Za-z0-9À-ÖØ-öø-ÿ' &]+$")) {
            throw new RestaurantNameException("The restaurant name should only contain letters, numbers, or spaces.");
        }

        if (restaurantName.length() < 3 || restaurantName.length() > 50) {
            throw new RestaurantNameException("The length of the restaurant name must be between 3 and 50 characters.");
        }

        // caseInsensitive restaurantName may be unique in the DB
        if (resDao.findByRestaurantNameIgnoreCase(restaurantName).isPresent()) {
            throw new RestaurantNameException("This name has already been used.");
        }

        //Crea una lista di parole o frasi inappropriati o offensivi e verifica se il nome del ristorante contiene tali termini.
    }

    public void validateOwner(){
        //deve già esistere questo Owner
    }




}






