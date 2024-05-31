package com.develhope.spring.models.dtos;

public class AddressDto {
    private String street;

    public AddressDto() {
    }

    public AddressDto(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
