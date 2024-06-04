package com.develhope.spring.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_address;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private Double number;

    @Column
    private String note;

    @Column
    private Double lat;

    @Column
    private Double lon;



    public AddressEntity() {
    }


    public AddressEntity(Long id_address, String city, String street, Double number, String note, Double lat, Double lon) {
        this.id_address = id_address;
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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
