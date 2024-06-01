package com.develhope.spring.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@MappedSuperclass
public class UserDetailsEntity extends UserEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private LocalDate birthDate;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private LocalDate creationDate = LocalDate.now();

    private LocalDate updateDate = LocalDate.now();

    public UserDetailsEntity() {
    }

    public UserDetailsEntity(String email, String password, Boolean isDeleted, Boolean isVerified, String name, String surname, LocalDate birthDate, String phoneNumber, LocalDate creationDate, LocalDate updateDate) {
        super(email, password, isDeleted, isVerified);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}
