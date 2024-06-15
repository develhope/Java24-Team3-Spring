package com.develhope.spring.models.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ScheduleDto {

    private String id;

    private DayOfWeek dayOfWeek;

    private LocalTime startingHour;

    private LocalTime endingHour;

    // CONSTRUCTORS

    public ScheduleDto() {
    }

    public ScheduleDto(String id, DayOfWeek dayOfWeek, LocalTime startingHour, LocalTime endingHour) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
    }

    // GETTERS AND SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(LocalTime startingHour) {
        this.startingHour = startingHour;
    }

    public LocalTime getEndingHour() {
        return endingHour;
    }

    public void setEndingHour(LocalTime endingHour) {
        this.endingHour = endingHour;
    }
}