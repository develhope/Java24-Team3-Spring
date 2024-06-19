package com.develhope.spring.models.entities;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "rider_workshift")
public class WorkshiftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "rider_id", nullable = false)
    private RiderEntity rider;

    @Column(name = "day", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "starting_hour", nullable = false)
    private LocalTime start;

    @Column(name = "ending_hour", nullable = false)
    private LocalTime end;

    // CONSTRUCTORS

    public WorkshiftEntity() {
    }

    public WorkshiftEntity(String id, RiderEntity rider, DayOfWeek dayOfWeek, LocalTime start, LocalTime end) {
        this.id = id;
        this.rider = rider;
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

    public RiderEntity getRider() {
        return rider;
    }

    public void setRider(RiderEntity rider) {
        this.rider = rider;
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