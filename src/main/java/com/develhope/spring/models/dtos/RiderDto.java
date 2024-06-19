package com.develhope.spring.models.dtos;

public class RiderDto extends UserDto {

    private double[] startingPosition;
    private double[] currentPosition;
    private Boolean isAvailable;

    public RiderDto() {
    }

    public RiderDto(String email, String password, Boolean isDeleted, Boolean isVerified, UserDetailsDto userDetails) {
        super(email, password, isDeleted, isVerified, userDetails);
        this.startingPosition = new double[2];
        this.currentPosition = new double[2];
        this.isAvailable = false;
    }

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

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}