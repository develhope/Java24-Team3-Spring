package com.develhope.spring.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_address;

    private String street;



    public AddressEntity() {
    }


    public AddressEntity( String street) {
        this.street = street;
    }

    public AddressEntity(Long id_address, String street) {
        this.id_address = id_address;
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getId_address() {
        return id_address;
    }

    public void setId_address(Long id_address) {
        this.id_address = id_address;
    }
}
