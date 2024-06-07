package com.develhope.spring.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity extends UserEntity {

    //AGGIUNGERE RELAZIONE CON ORDER

}