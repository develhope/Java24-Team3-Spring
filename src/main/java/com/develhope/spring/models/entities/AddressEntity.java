package com.develhope.spring.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_address;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String postcode;

    @Column
    private String street;

    @Column
    private Double number;

    @Column
    private String note;

    @Column
    private BigDecimal lat;

    @Column
    private BigDecimal lon;



    public AddressEntity() {
    }

    public AddressEntity(Long id_address, String country, String city, String postcode, String street, Double number, String note, BigDecimal lat, BigDecimal lon) {
        this.id_address = id_address;
        this.country = country;
        this.city = city;
        this.postcode = postcode;
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

    public void setNumber(Double number) {
        this.number = number;
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

    public void setNumber(String Double) {
        this.number = number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
