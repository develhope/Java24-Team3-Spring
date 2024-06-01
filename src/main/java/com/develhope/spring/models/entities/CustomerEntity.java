package com.develhope.spring.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class CustomerEntity extends UserDetailsEntity {

    public CustomerEntity() {
    }

    public CustomerEntity(String email, String password, Boolean isDeleted, Boolean isVerified, String name, String surname, LocalDate birthDate, String phoneNumber, LocalDate creationDate, LocalDate updateDate) {
        super(email, password, isDeleted, isVerified, name, surname, birthDate, phoneNumber, creationDate, updateDate);
    }

}
