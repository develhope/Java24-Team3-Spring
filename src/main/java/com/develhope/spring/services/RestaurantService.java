package com.develhope.spring.services;

import com.develhope.spring.dao.AddressDao;
import com.develhope.spring.dao.RestaurantDao;
import com.develhope.spring.exceptions.RestaurantNameException;
import com.develhope.spring.mappers.AddressMapper;
import com.develhope.spring.mappers.OperatingHoursMapper;
import com.develhope.spring.mappers.RestaurantMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.entities.AddressEntity;
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

    public ResponseModel createRestaurant(RestaurantDto resDto) {

        try {
            restaurantValidator.validateRestaurantName(resDto.getRestaurantName());
            RestaurantEntity resEntity = RestaurantMapper.toEntity(resDto);
            AddressEntity addressEntitySaved = addrDao.save(resEntity.getAddressEntity());
            resEntity.setAddressEntity(addressEntitySaved);


            RestaurantEntity resEntitySaved = resDao.save(resEntity);
            RestaurantDto resDtoSaved = RestaurantMapper.toDto(resEntitySaved);
            return new ResponseModel(ResponseCode.B, ResponseCode.B.getResponseType().toString() + ": "
                    + ResponseCode.B.getResponseType().getMessage() + " Details: "
                    + ResponseCode.B.getResponseCodeMessage(), resDtoSaved);
        } catch (RestaurantNameException e) {
            return new ResponseModel(ResponseCode.A, e.getMessage());
        }
    }

    public ResponseModel getRestaurantById(Long id) {
        Optional<RestaurantEntity> optRes = resDao.findById(id);

        if (optRes.isPresent()) {
            RestaurantEntity restaurantEntityFound = optRes.get();
            RestaurantDto resDtoFound = RestaurantMapper.toDto(restaurantEntityFound);
            return new ResponseModel(ResponseCode.C, ResponseCode.C.getResponseType().toString() + ": Details: "
                    + ResponseCode.C.getResponseType().getMessage() + " "
                    + ResponseCode.C.getResponseCodeMessage(), resDtoFound);
        } else {
            return new ResponseModel(ResponseCode.C, null);
        }
    }


    public ResponseModel getRestaurantByDeliveryOrTakeAway(boolean delivery, boolean takeAway) {
        List<RestaurantEntity> restaurantEntityList =
                delivery && takeAway ? resDao.findByIsTakeAwayAvailableTrueOrIsDeliveryAvailableTrue() :
                        takeAway ? resDao.findByIsTakeAwayAvailableTrue() :
                                delivery ? resDao.findByIsDeliveryAvailableTrue() : new ArrayList<>();

        return new ResponseModel(ResponseCode.E, ResponseCode.E.getResponseType().toString() + ": Details: "
                + ResponseCode.E.getResponseType().getMessage() + " "
                + ResponseCode.E.getResponseCodeMessage(), restaurantEntityList);

    }


    public ResponseModel updateRestaurant(long id, RestaurantDto resDto) {
        Optional<RestaurantEntity> optRes = resDao.findById(id);

        if (optRes.isPresent()) {
            RestaurantEntity restaurantEntityInDB = optRes.get();
            if (resDto != null) {
                if (resDto.getId_user() != null) {
                    return new ResponseModel(ResponseCode.F, "Non puoi inserire l'id del ristorante del json passato come body della richiesta.");
                }
                if (resDto.getEmail() != null) {
                    restaurantEntityInDB.setEmail(resDto.getEmail());
                }
                if (resDto.getRestaurantName() != null) {
                    restaurantEntityInDB.setRestaurantName(resDto.getRestaurantName());
                }
                if (resDto.getRestaurantPhoneNumber() != null) {
                    restaurantEntityInDB.setRestaurantPhoneNumber(resDto.getRestaurantPhoneNumber());
                }
                if (resDto.getAddressDto() != null) {
                    restaurantEntityInDB.setAddressEntity(AddressMapper.toEntity(resDto.getAddressDto()));
                }
                if (resDto.getDescription() != null) {
                    restaurantEntityInDB.setDescription(resDto.getDescription());
                }
                if (resDto.getIsDeliveryAvailable() != null) {
                    restaurantEntityInDB.setIsDeliveryAvailable(resDto.getIsDeliveryAvailable());
                }
                if (resDto.getIsTakeAwayAvailable() != null) {
                    restaurantEntityInDB.setIsTakeAwayAvaible(resDto.getIsTakeAwayAvailable());
                }
                if (resDto.getProductEntities() != null) {
                    restaurantEntityInDB.setProducts(resDto.getProductEntities());
                }
                if (resDto.getOperatingHoursDto() != null) {
                    restaurantEntityInDB.setOperatingHoursEntity(OperatingHoursMapper.toEntity(resDto.getOperatingHoursDto()));
                }

            }
            return new ResponseModel(ResponseCode.G, ResponseCode.G.getResponseType().toString() + ": Details: "
                    + ResponseCode.G.getResponseType().getMessage() + " "
                    + ResponseCode.G.getResponseCodeMessage(), restaurantEntityInDB);

        }
        return new ResponseModel(ResponseCode.C, ResponseCode.C.getResponseType().toString() + ": Details: "
                + ResponseCode.C.getResponseType().getMessage() + " "
                + ResponseCode.C.getResponseCodeMessage());
    }

    public ResponseModel deleteRestaurantById(Long id) {
        Optional<RestaurantEntity> optRes = resDao.findById(id);

        if (optRes.isPresent()) {
            RestaurantEntity restaurantEntityFound = optRes.get();
            RestaurantDto resDtoFound = RestaurantMapper.toDto(restaurantEntityFound);
            resDao.deleteById(id);
            return new ResponseModel(ResponseCode.H, ResponseCode.H.getResponseType().toString() + ": Details: "
                    + ResponseCode.H.getResponseType().getMessage() + " "
                    + ResponseCode.H.getResponseCodeMessage(), resDtoFound);
        } else {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": Details: "
                    + ResponseCode.D.getResponseType().getMessage() + " "
                    + ResponseCode.D.getResponseCodeMessage());
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
