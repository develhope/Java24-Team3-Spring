package com.develhope.spring.models.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class AdminEntity extends UserEntity {

    public AdminEntity(){

    }

    public AdminEntity(String email,
                          String password) {
        super(email, password);
    }
}
