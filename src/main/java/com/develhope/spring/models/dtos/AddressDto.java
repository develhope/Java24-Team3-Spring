package com.develhope.spring.models.dtos;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public class AddressDto {
    private String id_address;
    private String country;
    private String postcode;
    private String city;
    private String street;
    private String number;
    private String note;
    private BigDecimal[] coordinates = new BigDecimal[2];

    public AddressDto() {
    }

    public AddressDto(String id_address, String country, String postcode, String city, String street, String number, String note, BigDecimal[] coordinates) {
        this.id_address = id_address;
        this.country = country;
        this.postcode = postcode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.note = note;
        this.coordinates = coordinates;
    }

    public String getId_address() {
        return id_address;
    }

    public void setId_address(String id_address) {
        this.id_address = id_address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(BigDecimal[] coordinates) {
        this.coordinates = coordinates;
    }
}
