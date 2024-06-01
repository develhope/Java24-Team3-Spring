package com.develhope.spring.models.dtos;


import java.time.LocalDate;

public class CustomerDto extends UserDetailsDto {

    public CustomerDto() {
    }

    public CustomerDto(String email, String password, Boolean isDeleted, Boolean isVerified, String name, String surname, LocalDate birthDate, String phoneNumber, LocalDate creationDate, LocalDate updateDate) {
        super(email, password, isDeleted, isVerified, name, surname, birthDate, phoneNumber, creationDate, updateDate);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname);
    }

    @Override
    public LocalDate getBirthDate() {
        return super.getBirthDate();
    }

    @Override
    public void setBirthDate(LocalDate birthDate) {
        super.setBirthDate(birthDate);
    }

    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        super.setPhoneNumber(phoneNumber);
    }

    @Override
    public LocalDate getCreationDate() {
        return super.getCreationDate();
    }

    @Override
    public void setCreationDate(LocalDate creationDate) {
        super.setCreationDate(creationDate);
    }

    @Override
    public LocalDate getUpdateDate() {
        return super.getUpdateDate();
    }

    @Override
    public void setUpdateDate(LocalDate updateDate) {
        super.setUpdateDate(updateDate);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public Boolean getIsDeleted() {
        return super.getIsDeleted();
    }

    @Override
    public void setIsDeleted(Boolean isDeleted) {
        super.setIsDeleted(isDeleted);
    }

    @Override
    public Boolean getIsVerified() {
        return super.getIsVerified();
    }

    @Override
    public void setIsVerified(Boolean isVerified) {
        super.setIsVerified(isVerified);
    }
}
