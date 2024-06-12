package com.develhope.spring.models.entities;


import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name="turn")
public class OperatingHoursEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_oparatingHours;

//    @Enumerated(EnumType.STRING)
//    private DayOfTheWeek DayOfTheWeek;

    private DayOfWeek dayOfWeek;

    private LocalTime openingHour;

    private LocalTime closingHour;



    public OperatingHoursEntity() {
    }

    public OperatingHoursEntity(String id_oparatingHours, DayOfWeek dayOfWeek, LocalTime openingHour, LocalTime closingHour) {
        this.id_oparatingHours = id_oparatingHours;
        this.dayOfWeek = dayOfWeek;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public String getId_oparatingHours() {
        return id_oparatingHours;
    }

    public void setId_oparatingHours(String id_oparatingHours) {
        this.id_oparatingHours = id_oparatingHours;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(LocalTime openingHour) {
        this.openingHour = openingHour;
    }

    public LocalTime getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(LocalTime closingHour) {
        this.closingHour = closingHour;
    }
}

