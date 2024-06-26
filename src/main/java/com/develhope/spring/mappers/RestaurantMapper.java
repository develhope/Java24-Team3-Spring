package com.develhope.spring.mappers;

import com.develhope.spring.daos.OrderDao;
import com.develhope.spring.daos.OwnerDao;
import com.develhope.spring.daos.ReviewDao;
import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.dtos.RestaurantByLocationDto;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.dtos.RestaurantDtoCreate;
import com.develhope.spring.models.entities.ProductEntity;
import com.develhope.spring.models.entities.RestaurantEntity;
import com.develhope.spring.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantMapper {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    OperatingHoursMapper operatingHoursMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    RestaurantTypeMapper restaurantTypeMapper;

    @Autowired
    OwnerMapper ownerMapper;

    @Autowired
    OwnerDao ownerDao;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    ReviewService reviewService;

    public RestaurantDto toDto(RestaurantEntity restaurantEntity) {
        if (restaurantEntity == null) {
            return null;
        }

        List<ProductDto> productDtos = new ArrayList<>();

        if (restaurantEntity.getProductEntities() == null) {
            productDtos = null;
        } else {
            for (ProductEntity productEntity : restaurantEntity.getProductEntities()) {
                productDtos.add(
                        productMapper.toDto(productEntity)
                );
            }
        }

        return new RestaurantDto(
                restaurantEntity.getId(),
                ownerMapper.toDto(restaurantEntity.getOwnerEntity()),
                restaurantEntity.getRestaurantName(),
                restaurantEntity.getRestaurantEmail(),
                restaurantEntity.getRestaurantPhoneNumber(),
                addressMapper.toDto(restaurantEntity.getAddressEntity()),
                restaurantEntity.getDescription(),
                restaurantEntity.getIsDeliveryAvailable(),
                restaurantEntity.getIsTakeAwayAvailable(),
                operatingHoursMapper.toDto(restaurantEntity.getOperatingHoursEntities()),
                restaurantTypeMapper.toDto(restaurantEntity.getRestaurantTypeEntities()),
                productDtos,
                orderMapper.toDtos(restaurantEntity.getOrders()),
                reviewMapper.toDtos(restaurantEntity.getReviews()));
    }

    public RestaurantEntity toEntity(RestaurantDto resDto) {
        if (resDto == null) {
            return null;
        }

        List<ProductEntity> productEntities = new ArrayList<>();
        if (resDto.getProductDtos() == null) {
            productEntities = null;
        } else {
            for (ProductDto productDto : resDto.getProductDtos()) {
                productEntities.add(
                        productMapper.toEntity(productDto)
                );
            }
        }

        /*
        boolean delivery = resDto.getIsDeliveryAvailable() == null ? false : resDto.getIsDeliveryAvailable() ? true : false;
        boolean takeAway = resDto.getIsTakeAwayAvailable() == null ? false : resDto.getIsTakeAwayAvailable() ? true : false;
        */

        RestaurantEntity mappedRestaurant = new RestaurantEntity();

        mappedRestaurant.setId(resDto.getId());
        mappedRestaurant.setOwnerEntity(ownerMapper.toEntity(resDto.getOwnerDto()));
        mappedRestaurant.setRestaurantName(resDto.getRestaurantName());
        mappedRestaurant.setRestaurantEmail(resDto.getRestaurantEmail());
        mappedRestaurant.setRestaurantPhoneNumber(resDto.getRestaurantPhoneNumber());
        mappedRestaurant.setAddressEntity(addressMapper.toEntity(resDto.getAddressDto()));
        mappedRestaurant.setDescription(resDto.getDescription());
        mappedRestaurant.setIsDeliveryAvailable(resDto.getIsDeliveryAvailable());
        mappedRestaurant.setIsTakeAwayAvailable(resDto.getIsTakeAwayAvailable());
        mappedRestaurant.setOperatingHoursEntities(operatingHoursMapper.toEntity(resDto.getOperatingHoursDtos()));
        mappedRestaurant.setRestaurantTypeEntities(restaurantTypeMapper.toEntity(resDto.getRestaurantTypeDtos()));
        mappedRestaurant.setProductEntities(productEntities);
        mappedRestaurant.setOrders(orderMapper.toEntities(resDto.getOrders()));
        mappedRestaurant.setReviews(reviewMapper.toEntities(resDto.getReviews()));

        if (resDto.getOrders() == null || resDto.getOrders().isEmpty()) {

        }

        return mappedRestaurant;
    }

    public RestaurantDto toDto(RestaurantDtoCreate resDtocreate) {

        if (resDtocreate == null) {
            return null;
        }

        /*
        new RestaurantDto();
        */

        return new RestaurantDto(
                null,
                ownerMapper.toDto(ownerDao.getById(resDtocreate.getId_owner())),
                resDtocreate.getRestaurantName(),
                resDtocreate.getRestaurantEmail(),
                resDtocreate.getRestaurantPhoneNumber(),
                resDtocreate.getAddressDto(),
                resDtocreate.getDescription(),
                resDtocreate.getIsDeliveryAvailable(),
                resDtocreate.getIsTakeAwayAvailable(),
                resDtocreate.getOperatingHoursDtos(),
                resDtocreate.getRestaurantTypeDtos(),
                resDtocreate.getProductDtos(),
                resDtocreate.getOrders(),
                resDtocreate.getReviews()
                );
    }

    public RestaurantByLocationDto toRestaurantByLocationDto(RestaurantEntity restaurantEntity) {
        if (restaurantEntity == null) {
            return null;
        }

        double meanRating = reviewService.getMeanRatingByRestaurantId(restaurantEntity.getId());


        return new RestaurantByLocationDto(
                restaurantEntity.getId(),
                restaurantEntity.getRestaurantName(),
                null,
                meanRating,
                addressMapper.toDto(restaurantEntity.getAddressEntity()),
                null,
                null,
                restaurantEntity.getIsDeliveryAvailable(),
                restaurantEntity.getIsTakeAwayAvailable(),
                operatingHoursMapper.toDto(restaurantEntity.getOperatingHoursEntities())
        );
    }

}