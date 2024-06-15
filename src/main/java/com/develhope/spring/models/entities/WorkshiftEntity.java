package com.develhope.spring.models.entities;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "WORKSHIFT")
public class WorkshiftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "RIDER_ID", nullable = false)
    private RiderEntity rider;

    @Column(name = "DAY", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "STARTING_HOUR", nullable = false)
    private LocalTime startingHour;

    @Column(name = "ENDING_HOUR", nullable = false)
    private LocalTime endingHour;

    // CONSTRUCTORS

    public WorkshiftEntity() {
    }

    public WorkshiftEntity(String id, RiderEntity rider, DayOfWeek dayOfWeek, LocalTime startingHour, LocalTime endingHour) {
        this.id = id;
        this.rider = rider;
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