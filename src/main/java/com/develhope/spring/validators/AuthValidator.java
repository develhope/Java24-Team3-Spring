package com.develhope.spring.validators;

import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.models.dtos.RestaurantDtoCreate;
import com.develhope.spring.models.entities.UserEntity;
import com.develhope.spring.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;


@Component
public class AuthValidator {

    private final UserService userService;



    public AuthValidator(UserService userService) {
        this.userService = userService;
    }


    // controlla che chi ha fatto il login corrisponda allo user con l'id passato come parametro oppure ad un admin
    public void isItselfOrAdminById(String id) throws AuthenticationException {
        UserEntity currentUser = userService.getLoggedUser();
        if(!currentUser.getId().equals(id) && !currentUser.getClass().equals("com.develhope.spring.models.entities.AdminEntity")){throw new AuthenticationException("Only admins and users themselves have authorization to perform this operation.");};
    }

    public void isAdmin() throws AuthenticationException {
        UserEntity currentUser = userService.getLoggedUser();
        if(!currentUser.getClass().equals("com.develhope.spring.models.entities.AdminEntity")){throw new AuthenticationException("Only admins have authorization to perform this operation.");};
    }


    public void isAdminOrTheOwnerByRetsaurant(RestaurantDtoCreate restaurantDtoCreate) throws AuthenticationException {
        UserEntity currentUser = userService.getLoggedUser();
        if(!currentUser.getClass().equals("com.develhope.spring.models.entities.AdminEntity")){throw new AuthenticationException("Only admins have authorization to perform this operation.");};
    }

}
