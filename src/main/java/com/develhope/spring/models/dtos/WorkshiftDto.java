package com.develhope.spring.models.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class WorkshiftDto {

    private String id;

    private DayOfWeek dayOfWeek;

    private LocalTime start;

    private LocalTime end;

    // CONSTRUCTORS

    public WorkshiftDto() {
    }

    public WorkshiftDto(String id, DayOfWeek dayOfWeek, LocalTime start, LocalTime end) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.start = start;
        this.end = end;
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

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
}