package com.develhope.spring.models.entities;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @Column(name = "day", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "starting_hour", nullable = false)
    private LocalTime start;

    @Column(name = "ending_hour", nullable = false)
    private LocalTime end;

    // CONSTRUCTORS

    public ScheduleEntity() {
    }

    public ScheduleEntity(String id, RestaurantEntity restaurant, DayOfWeek dayOfWeek, LocalTime start, LocalTime end) {
        this.id = id;
        this.restaurant = restaurant;
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

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRider(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
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