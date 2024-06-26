package com.develhope.spring.models.dtos;

import java.util.List;

public class RiderDto extends UserDto {

    private double[] startingPosition = new double[2];

    private double[] currentPosition = new double[2];

    private boolean available = false;

    private List<WorkshiftDto> workshifts;

    public RiderDto() {
    }

    public RiderDto(UserDetailsDto userDetails,
                    String email,
                    String password,
                    Boolean isDeleted,
                    Boolean isVerified,
                    double[] startingPosition,
                    double[] currentPosition,
                    boolean available,
                    List<WorkshiftDto> workshifts) {
        super(email, password, isDeleted, isVerified, userDetails);
        this.startingPosition = startingPosition;
        this.currentPosition = currentPosition;
        this.available = available;
        this.workshifts = workshifts;
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

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<WorkshiftDto> getWorkshifts() {
        return workshifts;
    }

    public void setWorkshifts(List<WorkshiftDto> workshifts) {
        this.workshifts = workshifts;
    }

}