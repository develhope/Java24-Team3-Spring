package com.develhope.spring.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="turn")
public class OperatingHoursEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_oparatingHours;

    public OperatingHoursEntity() {
    }

    public OperatingHoursEntity(Long id_oparatingHours) {
        this.id_oparatingHours = id_oparatingHours;
    }

    public Long getId_oparatingHours() {
        return id_oparatingHours;
    }

    public void setId_oparatingHours(Long id_oparatingHours) {
        this.id_oparatingHours = id_oparatingHours;
    }
}
