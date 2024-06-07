package com.develhope.spring.models.dtos;

import com.develhope.spring.models.entities.UserDetailsEntity;
import java.math.BigDecimal;

public class RiderDto extends UserDto {

    private BigDecimal[] startingPosition;

    private BigDecimal[] currentPosition;

    public RiderDto() {
    }

    public RiderDto(String email, String password, Boolean isDeleted, Boolean isVerified, UserDetailsEntity userDetails) {
        super(email, password, isDeleted, isVerified, userDetails);
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
}