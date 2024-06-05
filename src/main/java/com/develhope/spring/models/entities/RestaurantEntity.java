package com.develhope.spring.models.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_restaurant;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String restaurantName;

    @Column
    private String restaurantPhoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adress_id")
    private AddressEntity addressEntity;

    @Column
    String description;
    @Column
    boolean isDeliveryAvailable;
    @Column
    boolean isTakeAwayAvailable;

    // cascade = CascadeType.ALL,
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "restaurant_product",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> productEntities;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "restaurant_turn",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "hour_id"))
    private List<OperatingHoursEntity> operatingHoursEntity;

    public RestaurantEntity() {
    }

    public RestaurantEntity(Long id_user, String email, String restaurantName, String restaurantPhoneNumber, AddressEntity addressEntity, String description, boolean isDeliveryAvaible, boolean isTakeAwayAvaible, List<ProductEntity> items, List<OperatingHoursEntity> operatingHours) {
        this.id_restaurant = id_user;
        this.email = email;
        this.restaurantName = restaurantName;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.addressEntity = addressEntity;
        this.description = description;
        this.isDeliveryAvailable = isDeliveryAvaible;
        this.isTakeAwayAvailable = isTakeAwayAvaible;
        this.productEntities = items;
        this.operatingHoursEntity = operatingHours;
    }


    public Long getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(Long id_restaurant) {
        this.id_restaurant = id_restaurant;
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

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

    public List<OperatingHoursEntity> getOperatingHoursEntity() {
        return operatingHoursEntity;
    }

    public void setOperatingHoursEntity(List<OperatingHoursEntity> operatingHoursEntity) {
        this.operatingHoursEntity = operatingHoursEntity;
    }

    public boolean getIsDeliveryAvailable() {
        return isDeliveryAvailable;
    }

    public void setIsDeliveryAvailable(boolean deliveryAvailable) {
        isDeliveryAvailable = deliveryAvailable;
    }

    public boolean getIsTakeAwayAvaible() {
        return isTakeAwayAvailable;
    }

    public void setIsTakeAwayAvaible(boolean takeAwayAvaible) {
        isTakeAwayAvailable = takeAwayAvaible;
    }


}

