package com.develhope.spring.models.dtos;

import java.math.BigDecimal;

public class AddressDto {
    private Long id_address;
    private String country;
    private String postcode;
    private String city;
    private String street;
    private Double number;
    private String note;
    private BigDecimal lat;
    private BigDecimal lon;

    public AddressDto() {
    }

    public AddressDto(Long id_address, String country, String postcode, String city, String street, Double number, String note, BigDecimal lat, BigDecimal lon) {
        this.id_address = id_address;
        this.country = country;
        this.postcode = postcode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.note = note;
        this.lat = lat;
        this.lon = lon;
    }

    public Long getId_address() {
        return id_address;
    }

    public void setId_address(Long id_address) {
        this.id_address = id_address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }
}