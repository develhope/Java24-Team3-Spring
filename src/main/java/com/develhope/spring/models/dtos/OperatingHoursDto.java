package com.develhope.spring.models.dtos;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import com.develhope.spring.models.costants.*;

public class OperatingHoursDto {

    private String id_oparatingHours;

//    @Enumerated(EnumType.STRING)
//    private DayOfTheWeek dayOfTheWeek;

    private DayOfWeek dayOfWeek;

    private LocalTime openingHour;

    private LocalTime closingHour;

    public OperatingHoursDto() {
    }

    public OperatingHoursDto(String id_oparatingHours, DayOfWeek dayOfWeek, LocalTime openingHour, LocalTime closingHour) {
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
