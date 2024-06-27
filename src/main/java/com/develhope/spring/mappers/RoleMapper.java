package com.develhope.spring.mappers;


import com.develhope.spring.models.costants.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public Role toRole(String roleString) throws Exception {
        return switch (roleString){
            case "admin" -> Role.ADMIN;
            case "customer" -> Role.CUSTOMER;
            case "owner" -> Role.OWNER;
            case "rider" -> Role.RIDER;
            default -> throw new Exception("This role doasn't exist.");
        };
    }
}
