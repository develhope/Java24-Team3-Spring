package com.develhope.spring.models.dtos;

import com.develhope.spring.models.entities.UserDetailsEntity;
import java.math.BigDecimal;

public class RiderDto extends UserDto {

    private BigDecimal[] startingPosition;
    private BigDecimal[] currentPosition;
    private Boolean isAvailable;

    public RiderDto() {
    }

    public RiderDto(String email, String password, Boolean isDeleted, Boolean isVerified, UserDetailsEntity userDetails) {
        super(email, password, isDeleted, isVerified, userDetails);
        this.startingPosition = new BigDecimal[2];
        this.currentPosition = new BigDecimal[2];
        this.isAvailable = false;
    }

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

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}