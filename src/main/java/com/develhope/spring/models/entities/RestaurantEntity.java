package com.develhope.spring.models.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private OwnerEntity ownerEntity;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column
    private String restaurantEmail;

    @Column
    private String restaurantPhoneNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;

    @Column
    String description;

    @Column
    boolean isDeliveryAvailable;

    @Column
    boolean isTakeAwayAvailable;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_turn",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "hour_id"))
    private List<OperatingHoursEntity> operatingHoursEntities;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "restaurant_restaurantType",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurantType"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "restaurantType"}))
    private List<RestaurantTypeEntity> restaurantTypeEntities;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_product",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> productEntities;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderEntity> orders;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ReviewEntity> reviews;

    public RestaurantEntity() {
    }

    public RestaurantEntity(String id,
                            OwnerEntity ownerEntity,
                            String restaurantName,
                            String restaurantEmail,
                            String restaurantPhoneNumber,
                            AddressEntity addressEntity,
                            String description,
                            boolean isDeliveryAvailable,
                            boolean isTakeAwayAvailable,
                            List<OperatingHoursEntity> operatingHoursEntity,
                            List<RestaurantTypeEntity> restaurantTypeEntity,
                            List<ProductEntity> productEntities,
                            List<OrderEntity> orders,
                            List<ReviewEntity> reviews) {
        this.id = id;
        this.ownerEntity = ownerEntity;
        this.restaurantName = restaurantName;
        this.restaurantEmail = restaurantEmail;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.addressEntity = addressEntity;
        this.description = description;
        this.isDeliveryAvailable = isDeliveryAvailable;
        this.isTakeAwayAvailable = isTakeAwayAvailable;
        this.operatingHoursEntities = operatingHoursEntity;
        this.restaurantTypeEntities = restaurantTypeEntity;
        this.productEntities = productEntities;
        this.orders = orders;
        this.reviews = reviews;
    }

    public OwnerEntity getOwnerEntity() {
        return ownerEntity;
    }

    public void setOwnerEntity(OwnerEntity ownerEntity) {
        this.ownerEntity = ownerEntity;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    public List<RestaurantTypeEntity> getRestaurantTypeEntities() {
        return restaurantTypeEntities;
    }

    public void setRestaurantTypeEntities(List<RestaurantTypeEntity> restaurantTypeEntities) {
        this.restaurantTypeEntities = restaurantTypeEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<OperatingHoursEntity> getOperatingHoursEntities() {
        return operatingHoursEntities;
    }

    public void setOperatingHoursEntities(List<OperatingHoursEntity> operatingHoursEntities) {
        this.operatingHoursEntities = operatingHoursEntities;
    }

    public boolean getIsDeliveryAvailable() {
        return isDeliveryAvailable;
    }

    public void setIsDeliveryAvailable(boolean deliveryAvailable) {
        isDeliveryAvailable = deliveryAvailable;
    }

    public boolean getIsTakeAwayAvailable() {
        return isTakeAwayAvailable;
    }

    public void setIsTakeAwayAvailable(boolean takeAwayAvaible) {
        isTakeAwayAvailable = takeAwayAvaible;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }
}