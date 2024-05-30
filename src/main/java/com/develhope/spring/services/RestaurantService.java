package com.develhope.spring.services;

import com.develhope.spring.dao.RestaurantDao;
import com.develhope.spring.exeptions.RestaurantNameException;
import com.develhope.spring.mappers.RestaurantMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.entities.RestaurantEntity;
import com.develhope.spring.validators.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantDao resDao;

    @Autowired
    private RestaurantValidator restaurantValidator;

    public ResponseModel createRestaurant(RestaurantDto resDto)  {

        try {
            restaurantValidator.validateRestaurantName(resDto.getRestaurantName());
            RestaurantEntity resEntity = RestaurantMapper.toEntity(resDto);
            RestaurantEntity resEntitySaved = resDao.saveAndFlush(resEntity);
            RestaurantDto resDtoSaved = RestaurantMapper.toDto(resEntitySaved);
            return new ResponseModel(ResponseCode.A, null, resDtoSaved);
        } catch (RestaurantNameException e) {
            return new ResponseModel(ResponseCode.A, e.getMessage() );
        }
    }

    public ResponseModel getRestaurantById(Long id)  {
        Optional<RestaurantEntity> optRes = resDao.findById(id);

        if(optRes.isPresent()) {
            RestaurantEntity restaurantEntityFound = optRes.get();
            RestaurantDto resDtoFound =  RestaurantMapper.toDto(restaurantEntityFound);
            return new ResponseModel(ResponseCode.C, null, resDtoFound);
        }
        else {
            return new ResponseModel(ResponseCode.C, null, null);
        }
    }


//    public ResponseModel getRestaurantByDeliveryOrTakeAway(boolean delivery, boolean takeAway)  {
//        List<RestaurantEntity> restaurantEntityList =
//                delivery&&takeAway? resDao.findByIsTakeAwayAvaibleTrueOrIsDeliveryAvaibleTrue() :
//                takeAway? resDao.findByIsTakeAwayAvaibleTrue() :
//                        delivery? resDao.findByIsDeliveryAvaibleTrue() : null;
//
//        return new ResponseModel(ResponseCode.C, null, null);
//
//
//
//
//
//
//        return null;
//    }

//    public RestaurantEntity updateRestaurant(RestaurantEntity res) {
//
//
//        Optional<RestaurantEntity> opt = resDao.findById(res.getRestaurantId());
//
//        if(opt.isPresent()) {
//            return resDao.save(res);
//        }
//        else
//            throw new RestaurantException("Restaurant not found.....");
//
//    }

    // Long id_user, String email, String restaurantName, String restaurantPhoneNumber, AddressEntity address,
    // String description, boolean isDeliveryAvaible, boolean isTakeAwayAvaible, List<ItemEntity> items, List<OperationHoursEntity> operatingHours

//    public RestaurantDao updatePatchRestaurant(Long id, RestaurantDto resDto) {
//        RestaurantEntity resEntity = RestaurantMapper.toEntity(resDto);
//        Optional<RestaurantEntity> optionalResEntityInDB = resDao.findById(id);
//        if (optionalResEntityInDB.isPresent()){
//            if (resEntity.get != null){
//                book.setAuthor(bookPatch.getAuthor());
//            }
//            if (bookPatch.getTitle() != null){
//                book.setTitle(bookPatch.getTitle());
//            }
//            if (bookPatch.getIsbn() != null){
//                book.setIsbn(bookPatch.getIsbn());
//            }
//            if (bookPatch.isAMasterpiece() != book.isIsAMasterpiece()){
//                book.setIsAMasterpiece(bookPatch.isAMasterpiece());
//            }
//            return updateBook(book);
//        } else {
//            return ;
//        }
//
//    }



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
