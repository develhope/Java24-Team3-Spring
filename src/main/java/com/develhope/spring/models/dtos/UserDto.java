package com.develhope.spring.models.dtos;

public class UserDto {

    private String id;

    private String email;

    private String password;

    private Boolean isDeleted = false;

    private Boolean isVerified = false;

    private UserDetailsDto userDetailsDto;

    public UserDto() {
    }

    public UserDto(String email, String password, Boolean isDeleted, Boolean isVerified, UserDetailsDto userDetails) {
        this.email = email;
        this.password = password;
        this.isDeleted = isVerified;
        this.isVerified = isDeleted;
        this.userDetailsDto = userDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public UserDetailsDto getUserDetailsDto() {
        return userDetailsDto;
    }

    public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
        this.userDetailsDto = userDetailsDto;
    }
}
