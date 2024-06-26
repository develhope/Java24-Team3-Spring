package com.develhope.spring.mappers;


import com.develhope.spring.models.costants.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public Role toRole(String roleString) throws Exception {
        return switch (roleString){
            case "ADMIN" -> Role.ADMIN;
            case "customer" -> Role.CUSTOMER;
            case "OWNER" -> Role.OWNER;
            case "DRIVER" -> Role.DRIVER;
            default -> throw new Exception("This role doasn't exist.");
        };
    }
}
