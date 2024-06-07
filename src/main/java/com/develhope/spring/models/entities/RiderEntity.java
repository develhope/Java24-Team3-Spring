package com.develhope.spring.models.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rider")
public class RiderEntity extends UserEntity {

    @Column(name = "starting_position")
    private BigDecimal[] startingPosition;

    @Column(name = "current_position")
    private BigDecimal[] currentPosition;

    // CONSTRUCTORS

    public RiderEntity() {
    }

    public RiderEntity(String email, String password, Boolean isDeleted, Boolean isVerified, UserDetailsEntity userDetails) {
        super(email, password, isDeleted, isVerified, userDetails);
    }

    // GETTERS AND SETTERS

    public BigDecimal[] getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(BigDecimal[] startingPosition) {
        this.startingPosition = startingPosition;
    }

    public BigDecimal[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(BigDecimal[] currentPosition) {
        this.currentPosition = currentPosition;
    }

}