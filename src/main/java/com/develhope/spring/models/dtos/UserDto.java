package com.develhope.spring.models.dtos;

import com.develhope.spring.models.entities.UserDetailsEntity;

public class UserDto {

    private Long id;

    private String email;

    private String password;

    private Boolean isDeleted;

    private Boolean isVerified;

    private UserDetailsEntity userDetails;

    public UserDto() {
    }

    public UserDto(String email, String password, Boolean isDeleted, Boolean isVerified, UserDetailsEntity userDetails) {
        this.email = email;
        this.password = password;
        this.isDeleted = isDeleted;
        this.isVerified = isVerified;
        this.userDetails = userDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public UserDetailsEntity getUserDetails() {
        return userDetails;
    }

    public void setUserDetailsEntity(UserDetailsEntity userDetails) {
        this.userDetails = userDetails;
    }
}
