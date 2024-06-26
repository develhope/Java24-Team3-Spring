package com.develhope.spring.models.dtos;

public class RegisterUserDto {

    private String id;
    private String email;
    private String password;



    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterUserDto(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RegisterUserDto{" +
                "username='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
