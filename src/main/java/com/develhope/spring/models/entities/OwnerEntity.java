package com.develhope.spring.models.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "owner")
public class OwnerEntity extends UserEntity{
}
