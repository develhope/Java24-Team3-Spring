package com.develhope.spring.models.entities;

import jakarta.persistence.*;

@MappedSuperclass
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private Boolean isDeleted = false;

    private Boolean isVerified = false;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_details_id")
    private UserDetailsEntity userDetails;

    public UserEntity() {
    }

    public UserEntity(String email, String password, Boolean isDeleted, Boolean isVerified, UserDetailsEntity userDetails) {
        this.email = email;
        this.password = password;
        this.isDeleted = isDeleted;
        this.isVerified = isVerified;
        this.userDetails = userDetails;
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

    public UserDetailsEntity getUserDetails() {
        return userDetails;
    }

    public void setUserDetailsEntity(UserDetailsEntity userDetails) {
        this.userDetails = userDetails;
    }
}
