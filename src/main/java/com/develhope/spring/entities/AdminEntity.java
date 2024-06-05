package com.develhope.spring.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "ADMIN")
public class AdminEntity extends UserEntity{
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
