package com.develhope.spring.services;

import com.develhope.spring.daos.*;
import com.develhope.spring.mappers.*;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.*;
import com.develhope.spring.models.entities.*;
import com.develhope.spring.utils.DistanceCalculator;
import com.develhope.spring.utils.TimeCalculator;
import com.develhope.spring.validators.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    BigDecimal velocityKmPerH = BigDecimal.valueOf(30);

    // Convertire la velocità da km/h a m/min
    BigDecimal velocityMPerMin = velocityKmPerH.multiply(BigDecimal.valueOf(1000))
            .divide(BigDecimal.valueOf(60), 10, RoundingMode.HALF_UP);





    @Autowired
    private RestaurantValidator restaurantValidator;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    OperatingHoursMapper operatingHoursMapper;

    @Autowired
    RestaurantTypeMapper restaurantTypeMapper;

    @Autowired
    AddressValidator addressValidator;

    @Autowired
    ContactValidator contactValidator;

    @Autowired
    OperatingHoursValidator operatingHoursValidator;

    @Autowired
    RestaurantTypeValidator restaurantTypeValidator;

    @Autowired
    ProductValidator productValidator;

    @Autowired
    IdValidator idValidator;

    @Autowired
    DistanceCalculator distanceCalculator;

    @Autowired
    AddressService addrService;

    @Autowired
    OwnerService ownerService;

    @Autowired
    ProductService productService;


//    public RestaurantDto createRestaurant(RestaurantDto resDto) throws Exception {
//
//
//        // validate and save dependences in DB (dependeces are vadidated when tey are created)
//        idValidator.noId(resDto.getId_restaurant());
//        restaurantValidator.validateRestaurantName(resDto.getRestaurantName());
//        contactValidator.validatePhoneNumber(resDto.getRestaurantPhoneNumber());
//        contactValidator.validateEmail(resDto.getRestaurantEmail());
//        restaurantTypeValidator.validateRestaurantType(resDto.getRestaurantTypeDtos());
//        resDto.setOwnerDto(ownerService.createOwner(resDto.getOwnerDto()));
//        resDto.setAddressDto(addrService.createAddress(resDto.getAddressDto()));
//        resDto.setOperatingHoursDtos(operatingHoursService.createOperatingHours(resDto.getOperatingHoursDtos()));
//        resDto.setProductDtos(productService.createProducts(resDto.getProductDtos()));
//
//
//        RestaurantEntity resEntity = restaurantMapper.toEntity(resDto);
//        RestaurantEntity resEntitySaved = resDao.save(resEntity);
//        RestaurantDto resDtoSaved = restaurantMapper.toDto(resEntitySaved);
//        return resDtoSaved;
//    }


    public ResponseModel createRestaurant(RestaurantDtoCreate resDtoCreate) {

        try {
            //validation
            restaurantValidator.validateRestaurantName(resDtoCreate.getRestaurantName());
            contactValidator.validatePhoneNumber(resDtoCreate.getRestaurantPhoneNumber());
            contactValidator.validateEmail(resDtoCreate.getRestaurantEmail());
            AddressDto validAddressDto = addressValidator.getValidAddress(resDtoCreate.getAddressDto());
            restaurantTypeValidator.validateRestaurantType(resDtoCreate.getRestaurantTypeDtos());
            productValidator.validateProduct(resDtoCreate.getProductDtos());


            if (resDtoCreate.getId_owner() == null) throw new Exception("It's necessary id of the owner.");
            if (!ownerDao.findById(resDtoCreate.getId_owner()).isPresent())
                throw new Exception("The id " + resDtoCreate.getId_owner() + "is not present in owers database.");

            operatingHoursValidator.validateOperatingHours(resDtoCreate.getOperatingHoursDtos());


            // convert Dto to Entity and save in DB
            RestaurantEntity resEntity = restaurantMapper.toEntity(restaurantMapper.toDto(resDtoCreate));
            AddressEntity addressEntitySaved = addrDao.save(addressMapper.toEntity(validAddressDto));
            List<OperatingHoursEntity> operatingHoursEntitiesSaved = operatingHoursDao.saveAll(resEntity.getOperatingHoursEntities());
            List<RestaurantTypeEntity> restaurantTypeEntitiesSaved = restaurantTypeDao.saveAll(resEntity.getRestaurantTypeEntities());
            List<ProductEntity> productEntitiesSaved = productDao.saveAll(resEntity.getProductEntities());

            // update Dto with ids and retun Dto
            resEntity.setOwnerEntity(resEntity.getOwnerEntity());
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
        for (RestaurantEntity r : restaurantEntityList) {
            restaurantDtoList.add(restaurantMapper.toDto(r));
        }

        return new ResponseModel(ResponseCode.E, restaurantDtoList);

    }

    // radius in meter
    public ResponseModel getRestaurantWithinRadious(AddressDto addressDto, BigDecimal radius)  {
        try {
            AddressDto validAddress = addressValidator.getValidAddress(addressDto);
            List<RestaurantEntity> restaurantEntities = resDao.findAll();
            List<RestaurantByLocationDto> nearRestaurants = new ArrayList<>();
            for (RestaurantEntity r : restaurantEntities) {
                AddressEntity a = r.getAddressEntity();

                // distance in meter
                BigDecimal distance = DistanceCalculator.calculateDistance(validAddress.getCoordinates()[0], a.getLat(), validAddress.getCoordinates()[1], a.getLon(), BigDecimal.ZERO, BigDecimal.ZERO);
                if (distance.compareTo(radius) <= 0) {

                    nearRestaurants.add(restaurantMapper.toRestaurantByLocationDto(r)
                            .setDistance(distance)
                            .setDeliveryTime(
                                    TimeCalculator.calculateTime(distance, velocityMPerMin)//time in minutes; velocity is 30 km/h
                            )
                    );
                }
            }
            return new ResponseModel(ResponseCode.E, nearRestaurants);
        } catch (Exception e) {
            return new ResponseModel(ResponseCode.E).addMessageDetails(e.getMessage());
        }
    }


    public ResponseModel updateRestaurant(String id, RestaurantDto resDtoUpdates) {
        Optional<RestaurantEntity> optRes = resDao.findById(id);

        if (optRes.isPresent()) {
            RestaurantEntity restaurantEntityInDB = optRes.get();
            if (resDtoUpdates != null) {
                try {
                    idValidator.noId(resDtoUpdates.getId());

                    RestaurantEntity resEntityUpdates = restaurantMapper.toEntity(resDtoUpdates);
                    if (resEntityUpdates.getId() != null) {
                        return new ResponseModel(ResponseCode.F);
                    }
                    if (resEntityUpdates.getOwnerEntity() != null) {
                        throw new Exception("Ownership of a restaurant can only be modified through a specific endpoit.");
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
                        throw new Exception("Address of a restaurant can only be modified through a specific endpoit.");
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
                        throw new Exception("Operating Hours of a restaurant can only be modified through a specific endpoit.");
                    }
                    if (resEntityUpdates.getRestaurantTypeEntities() != null) {
                        restaurantEntityInDB.setRestaurantTypeEntities(restaurantTypeDao.saveAll(resEntityUpdates.getRestaurantTypeEntities()));
                    }
                    if (resEntityUpdates.getProductEntities() != null) {
                        throw new Exception("Products of a restaurant can only be modified through a specific endpoit.");
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

//    // new valid address
//    AddressDto validAddressDto = addressValidator.getValidAddress(resDtoUpdates.getAddressDto());
//    AddressEntity validAddressEntity = AddressMapper.toEntity(validAddressDto);
//
//    // delete old address
//                        addrDao.deleteById(restaurantEntityInDB.getAddressEntity().getId_address());
//
//    // save new address and set new restaurant address
//                        restaurantEntityInDB.setAddressEntity(addrDao.save(validAddressEntity));

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


    public ResponseModel getRestaurantByType(List<String> restaurantTypeStrings) {
        if (restaurantTypeStrings == null) return  new ResponseModel(ResponseCode.E).addMessageDetails("You have chosen no restaurantType.");
        List<RestaurantEntity> restaurantEntities = resDao.findByRestaurantTypeEntityIn(restaurantTypeStrings);
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        for(RestaurantEntity r:restaurantEntities){
            restaurantDtos.add(restaurantMapper.toDto(r));
        }
        return new ResponseModel(ResponseCode.E, restaurantDtos);
        //return restaurantDtos;
    }
}
