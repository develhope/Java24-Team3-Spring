package com.develhope.spring.services;

import com.develhope.spring.dao.AddressDao;
import com.develhope.spring.dao.RestaurantDao;
import com.develhope.spring.exceptions.RestaurantNameException;
import com.develhope.spring.mappers.AddressMapper;
import com.develhope.spring.mappers.OperatingHoursMapper;
import com.develhope.spring.mappers.RestaurantMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.entities.AddressEntity;
import com.develhope.spring.models.entities.ProductTypeEntity;
import com.develhope.spring.models.entities.RestaurantEntity;
import com.develhope.spring.validators.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantDao resDao;

    @Autowired
    private AddressDao addrDao;

    double restaurantRadiousKm = 10;

    @Autowired
    private RestaurantValidator restaurantValidator;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    OperatingHoursMapper operatingHoursMapper;

    public ResponseModel createRestaurant(RestaurantDto resDto) {

        try {
            restaurantValidator.validateRestaurantName(resDto.getRestaurantName());
            RestaurantEntity resEntity = restaurantMapper.toEntity(resDto);
            AddressEntity addressEntitySaved = addrDao.save(resEntity.getAddressEntity());
            resEntity.setAddressEntity(addressEntitySaved);


            RestaurantEntity resEntitySaved = resDao.save(resEntity);
            RestaurantDto resDtoSaved = restaurantMapper.toDto(resEntitySaved);
            return new ResponseModel(ResponseCode.B, resDtoSaved);
        } catch (RestaurantNameException e) {
            return new ResponseModel(ResponseCode.A, e.getMessage());
        }
    }

    public ResponseModel getRestaurantById(Long id) {
        Optional<RestaurantEntity> optRes = resDao.findById(id);

        if (optRes.isPresent()) {
            RestaurantEntity restaurantEntityFound = optRes.get();
            RestaurantDto resDtoFound = restaurantMapper.toDto(restaurantEntityFound);
            return new ResponseModel(ResponseCode.C, resDtoFound);
        } else {
            return new ResponseModel(ResponseCode.C, null);
        }
    }


    public ResponseModel getRestaurantByDeliveryOrTakeAway(boolean delivery, boolean takeAway) {
        List<RestaurantEntity> restaurantEntityList =
                delivery && takeAway ? resDao.findByIsTakeAwayAvailableTrueOrIsDeliveryAvailableTrue() :
                        takeAway ? resDao.findByIsTakeAwayAvailableTrue() :
                                delivery ? resDao.findByIsDeliveryAvailableTrue() : new ArrayList<>();

        List<RestaurantDto> restaurantDtoList = new ArrayList<>();
        for (RestaurantEntity r : restaurantEntityList){
            restaurantDtoList.add(restaurantMapper.toDto(r));
        }

        return new ResponseModel(ResponseCode.E, restaurantDtoList);

    }


    public ResponseModel updateRestaurant(long id, RestaurantDto resDto) {
        Optional<RestaurantEntity> optRes = resDao.findById(id);

        if (optRes.isPresent()) {
            RestaurantEntity restaurantEntityInDB = optRes.get();
            if (resDto != null) {
                RestaurantEntity resEntity = restaurantMapper.toEntity(resDto);
                if (resEntity.getId_restaurant() != null) {
                    return new ResponseModel(ResponseCode.F);
                } else {
                    resEntity.setId_restaurant(id);
                }
                if (resEntity.getEmail() != null) {
                    restaurantEntityInDB.setEmail(resEntity.getEmail());
                }
                if (resEntity.getRestaurantName() != null) {
                    restaurantEntityInDB.setRestaurantName(resEntity.getRestaurantName());
                }
                if (resEntity.getRestaurantPhoneNumber() != null) {
                    restaurantEntityInDB.setRestaurantPhoneNumber(resEntity.getRestaurantPhoneNumber());
                }
                if (resEntity.getAddressEntity() != null) {
                    restaurantEntityInDB.setAddressEntity(resEntity.getAddressEntity());
                }
                if (resEntity.getDescription() != null) {
                    restaurantEntityInDB.setDescription(resEntity.getDescription());
                }
                if (resDto.getIsDeliveryAvailable() != null) {
                    // problema dei boolean
                    restaurantEntityInDB.setIsDeliveryAvailable(resDto.getIsDeliveryAvailable());
                }
                if (resDto.getIsTakeAwayAvailable() != null) {
                    // problema dei boolean
                    restaurantEntityInDB.setIsTakeAwayAvaible(resEntity.getIsTakeAwayAvaible());
                }
                if (resEntity.getProductEntities() != null) {
                    restaurantEntityInDB.setProductEntities(resEntity.getProductEntities());
                }
                if (resEntity.getOperatingHoursEntity() != null) {
                    restaurantEntityInDB.setOperatingHoursEntity(resEntity.getOperatingHoursEntity());
                }

            }
            resDao.save(restaurantEntityInDB);
            RestaurantDto modifiedResDto = restaurantMapper.toDto(restaurantEntityInDB);
            return new ResponseModel(ResponseCode.G, modifiedResDto);

        } else {
            return new ResponseModel(ResponseCode.D);
        }
    }

    public ResponseModel deleteRestaurantById(Long id) {
        Optional<RestaurantEntity> optRes = resDao.findById(id);

        if (optRes.isPresent()) {
            RestaurantEntity restaurantEntityFound = optRes.get();
            RestaurantDto resDtoFound = restaurantMapper.toDto(restaurantEntityFound);
            resDao.deleteById(id);
            return new ResponseModel(ResponseCode.H, resDtoFound);
        } else {
            return new ResponseModel(ResponseCode.D);
        }
    }



//
//    public List<RestaurantEntity> viewNearByRestaurant(Address address) throws RestaurantException {
//
//
//        List<RestaurantEntity> rests = resDao.findByAddress(address);
//
//
//        if(rests.size()>0)
//            return rests;
//        else
//            throw new RestaurantException("Restaurant not found with this address :"+address);
//    }
//


}
