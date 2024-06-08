package com.develhope.spring.services;

import com.develhope.spring.daos.*;
import com.develhope.spring.mappers.AddressMapper;
import com.develhope.spring.mappers.OperatingHoursMapper;
import com.develhope.spring.mappers.RestaurantMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.AddressDto;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.entities.*;
import com.develhope.spring.validators.AddressValidator;
import com.develhope.spring.validators.ContactValidator;
import com.develhope.spring.validators.RestaurantValidator;
import jakarta.transaction.Transactional;
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
    @Autowired
    private OwnerDao ownerDao;
    @Autowired
    private RestaurantTypeDao restaurantTypeDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private OperatingHoursDao operatingHoursDao;

    double restaurantRadiousKm = 10;

    @Autowired
    private RestaurantValidator restaurantValidator;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    OperatingHoursMapper operatingHoursMapper;

    @Autowired
    AddressValidator addressValidator;

    @Autowired
    ContactValidator contactValidator;



    public ResponseModel createRestaurant(RestaurantDto resDto) {

        try {
            //validation
            restaurantValidator.validateRestaurantName(resDto.getRestaurantName());
            AddressDto validAddressDto = addressValidator.getValidAddress(resDto.getAddressDto());
            contactValidator.validatePhoneNumber(resDto.getRestaurantPhoneNumber());
            contactValidator.validateEmail(resDto.getRestaurantEmail());


            // convert Dto to Entity and save in DB
            RestaurantEntity resEntity = restaurantMapper.toEntity(resDto);
            OwnerEntity ownerEntitySaved = ownerDao.save(resEntity.getOwnerEntity());
            AddressEntity addressEntitySaved = addrDao.save(addressMapper.toEntity(validAddressDto));
            List<OperatingHoursEntity> operatingHoursEntitiesSaved = operatingHoursDao.saveAll(resEntity.getOperatingHoursEntities());
            List<RestaurantTypeEntity> restaurantTypeEntitiesSaved = restaurantTypeDao.saveAll(resEntity.getRestaurantTypeEntities());
            List<ProductEntity> productEntitiesSaved = productDao.saveAll(resEntity.getProductEntities());

            // update Dto with ids and retun Dto
            resEntity.setOwnerEntity(ownerEntitySaved);
            resEntity.setAddressEntity(addressEntitySaved);
            resEntity.setOperatingHoursEntities(operatingHoursEntitiesSaved);
            resEntity.setRestaurantTypeEntities(restaurantTypeEntitiesSaved);
            resEntity.setProductEntities(productEntitiesSaved);
            RestaurantEntity resEntitySaved = resDao.save(resEntity);
            RestaurantDto resDtoSaved = restaurantMapper.toDto(resEntitySaved);
            return new ResponseModel(ResponseCode.B, resDtoSaved);
        } catch (Exception e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    public ResponseModel getRestaurantById(String id) {
        Optional<RestaurantEntity> optRes = resDao.findById(id);

        if (optRes.isPresent()) {
            RestaurantEntity restaurantEntityFound = optRes.get();
            RestaurantDto resDtoFound = restaurantMapper.toDto(restaurantEntityFound);
            return new ResponseModel(ResponseCode.C, resDtoFound);
        } else {
            return new ResponseModel(ResponseCode.D);
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


    public ResponseModel updateRestaurant(String id, RestaurantDto resDtoUpdates) {
        Optional<RestaurantEntity> optRes = resDao.findById(id);

        if (optRes.isPresent()) {
            RestaurantEntity restaurantEntityInDB = optRes.get();
            if (resDtoUpdates != null) {
                try{
                    RestaurantEntity resEntityUpdates = restaurantMapper.toEntity(resDtoUpdates);
                    if (resEntityUpdates.getId_restaurant() != null) {
                        return new ResponseModel(ResponseCode.F);
                    } else {
                        resEntityUpdates.setId_restaurant(id);
                    }
                    if (resEntityUpdates.getOwnerEntity() != null) {
                        restaurantEntityInDB.setOwnerEntity(ownerDao.save(resEntityUpdates.getOwnerEntity()));
                    }
                    if (resEntityUpdates.getRestaurantName() != null) {
                        restaurantValidator.validateRestaurantName(resEntityUpdates.getRestaurantName());
                        restaurantEntityInDB.setRestaurantName(resEntityUpdates.getRestaurantName());
                    }
                    if (resEntityUpdates.getRestaurantEmail() != null) {
                        contactValidator.validateEmail(resEntityUpdates.getRestaurantEmail());
                        restaurantEntityInDB.setRestaurantEmail(resEntityUpdates.getRestaurantEmail());
                    }
                    if (resEntityUpdates.getRestaurantPhoneNumber() != null) {
                        contactValidator.validatePhoneNumber(resEntityUpdates.getRestaurantPhoneNumber());
                        restaurantEntityInDB.setRestaurantPhoneNumber(resEntityUpdates.getRestaurantPhoneNumber());
                    }
                    if (resEntityUpdates.getAddressEntity() != null) {
                        AddressDto validAddressDto = addressValidator.getValidAddress(resDtoUpdates.getAddressDto());
                        AddressEntity validAddressEntity = AddressMapper.toEntity(validAddressDto);
                        restaurantEntityInDB.setAddressEntity(addrDao.save(validAddressEntity));
                    }
                    if (resEntityUpdates.getDescription() != null) {
                        restaurantEntityInDB.setDescription(resEntityUpdates.getDescription());
                    }
                    if (resDtoUpdates.getIsDeliveryAvailable() != null) {
                        restaurantEntityInDB.setIsDeliveryAvailable(resDtoUpdates.getIsDeliveryAvailable());
                    }
                    if (resDtoUpdates.getIsTakeAwayAvailable() != null) {
                        restaurantEntityInDB.setIsTakeAwayAvaible(resEntityUpdates.getIsTakeAwayAvaible());
                    }
                    if (resEntityUpdates.getOperatingHoursEntities() != null) {
                        restaurantEntityInDB.setOperatingHoursEntities(operatingHoursDao.saveAll(resEntityUpdates.getOperatingHoursEntities()));
                    }
                    if (resEntityUpdates.getRestaurantTypeEntities() != null) {
                        restaurantEntityInDB.setRestaurantTypeEntities(restaurantTypeDao.saveAll(resEntityUpdates.getRestaurantTypeEntities()));
                    }
                    if (resEntityUpdates.getProductEntities() != null) {
                        restaurantEntityInDB.setProductEntities(productDao.saveAll(resEntityUpdates.getProductEntities()));
                    }
                } catch (Exception e) {
                return new ResponseModel(ResponseCode.K).addMessageDetails(e.getMessage());
            }


            }
            resDao.save(restaurantEntityInDB);
            RestaurantDto modifiedResDto = restaurantMapper.toDto(restaurantEntityInDB);
            return new ResponseModel(ResponseCode.G, modifiedResDto);

        } else {
            return new ResponseModel(ResponseCode.D);
        }
    }

    @Transactional
    public ResponseModel deleteRestaurantById(String id) {
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
