package com.develhope.spring.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_address;

    public AddressEntity() {
    }

    public AddressEntity(Long id_address) {
        this.id_address = id_address;
    }

    public Long getId_address() {
        return id_address;
    }

    public void setId_address(Long id_address) {
        this.id_address = id_address;
    }
}
