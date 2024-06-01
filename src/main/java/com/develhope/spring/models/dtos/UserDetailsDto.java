package com.develhope.spring.models.dtos;


import java.time.LocalDate;

public class UserDetailsDto extends UserDto {

    private String name;

    private String surname;

    private LocalDate birthDate;

    private String phoneNumber;

    private LocalDate creationDate;

    private LocalDate updateDate;

    public UserDetailsDto() {
    }

    public UserDetailsDto(String email, String password, Boolean isDeleted, Boolean isVerified, String name, String surname, LocalDate birthDate, String phoneNumber, LocalDate creationDate, LocalDate updateDate) {
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
