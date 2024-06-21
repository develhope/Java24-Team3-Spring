package com.develhope.spring.models.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "rider")
public class RiderEntity extends UserEntity {

    @Column(name = "starting_position")
    private double[] startingPosition;

    @Column(name = "current_position")
    private double[] currentPosition;

    @Column(name = "available", nullable = false)
    private Boolean available = false;

    @OneToMany(cascade = CascadeType.ALL)
    private List<WorkshiftEntity> workshifts;

    // CONSTRUCTORS

    public RiderEntity() {
    }

    public RiderEntity(String email, String password, Boolean isDeleted, Boolean isVerified, UserDetailsEntity userDetails, List<WorkshiftEntity> workshifts) {
        super(email, password, isDeleted, isVerified, userDetails);
        this.startingPosition = new double[2];
        this.currentPosition = new double[2];
        this.available = false;
        this.workshifts = workshifts;
    }

    // GETTERS AND SETTERS

    public double[] getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(double[] startingPosition) {
        this.startingPosition = startingPosition;
    }

    public double[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(double[] currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<WorkshiftEntity> getWorkshifts() {
        return workshifts;
    }

    public void setWorkshifts(List<WorkshiftEntity> workshifts) {
        this.workshifts = workshifts;
    }

}