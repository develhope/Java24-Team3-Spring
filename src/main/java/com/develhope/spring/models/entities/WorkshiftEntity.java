package com.develhope.spring.models.entities;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "workshift")
public class WorkshiftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "day", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "starting_hour", nullable = false)
    private LocalTime end;

    @Column(name = "ending_hour", nullable = false)
    private LocalTime start;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rider_id", nullable = false, referencedColumnName = "id")
    private RiderEntity rider;

    // CONSTRUCTORS

    public WorkshiftEntity() {
    }

    public WorkshiftEntity(String id,
                           DayOfWeek dayOfWeek,
                           LocalTime end,
                           LocalTime start,
                           RiderEntity rider) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.end = end;
        this.start = start;
        this.rider = rider;
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

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public RiderEntity getRider() {
        return rider;
    }

    public void setRider(RiderEntity rider) {
        this.rider = rider;
    }

}