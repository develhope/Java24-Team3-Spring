package com.develhope.spring.models.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_user;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String restaurantName;

    @Column(nullable = true, unique = true)
    private String restaurantPhoneNumber;

    @OneToOne
    private AddressEntity addressEntity;

    String description;

    boolean isDeliveryAvaible;
    boolean isTakeAwayAvaible;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ItemEntity> items = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OperationHoursEntity> operatingHours;


    public RestaurantEntity(Long id_user, String email, String restaurantName, String restaurantPhoneNumber, AddressEntity addressEntity, String description, boolean isDeliveryAvaible, boolean isTakeAwayAvaible, List<ItemEntity> items, List<OperationHoursEntity> operatingHours) {
        this.id_user = id_user;
        this.email = email;
        this.password = password;
        this.restaurantName = restaurantName;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.addressEntity = addressEntity;
        this.description = description;
        this.isDeliveryAvaible = isDeliveryAvaible;
        this.isTakeAwayAvaible = isTakeAwayAvaible;
        this.items = items;
        this.operatingHours = operatingHours;
    }



    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPhoneNumber() {
        return restaurantPhoneNumber;
    }

    public void setRestaurantPhoneNumber(String restaurantPhoneNumber) {
        this.restaurantPhoneNumber = restaurantPhoneNumber;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDeliveryAvaible() {
        return isDeliveryAvaible;
    }

    public void setIsDeliveryAvaible(boolean deliveryAvaible) {
        isDeliveryAvaible = deliveryAvaible;
    }

    public boolean getIsTakeAwayAvaible() {
        return isTakeAwayAvaible;
    }

    public void setIsTakeAwayAvaible(boolean takeAwayAvaible) {
        isTakeAwayAvaible = takeAwayAvaible;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public List<OperationHoursEntity> getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(List<OperationHoursEntity> operatingHours) {
        this.operatingHours = operatingHours;
    }
}

