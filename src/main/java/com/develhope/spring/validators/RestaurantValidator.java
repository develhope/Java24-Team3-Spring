package com.develhope.spring.validators;

import com.develhope.spring.dao.RestaurantDao;
import com.develhope.spring.exeptions.RestaurantNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantValidator {
    @Autowired
    RestaurantDao restaurantDao;

    public void validateRestaurantName(String restaurantName) throws RestaurantNameException {
        if (restaurantName == null || restaurantName.isEmpty()) {
            throw new RestaurantNameException("The restaurant name cannot be empty.");
        }

        if (!restaurantName.matches("^[a-zA-Z0-9 ]*$")) {
            throw new RestaurantNameException("The restaurant name should only contain letters, numbers, or spaces.");
        }

        if (restaurantName.length() < 3 || restaurantName.length() > 50) {
            throw new RestaurantNameException("The length of the restaurant name must be between 3 and 50 characters.");
        }

        //Controlla se il nome del ristorante è già stato utilizzato da un altro ristorante registrato nell’applicazione. Assicurati che ogni nome sia univoco.
        // cerco se i ristoranti per nome, e lancio un eccezione se trovo un altro ristorante con lo stesso nome
        // faccio il controllo sui nomi case insensitive
        if (restaurantName == "ciao") {

            throw new RestaurantNameException("This name has already been used.");
        }

        //Crea una lista di parole o frasi inappropriati o offensivi e verifica se il nome del ristorante contiene tali termini.

    }




}






