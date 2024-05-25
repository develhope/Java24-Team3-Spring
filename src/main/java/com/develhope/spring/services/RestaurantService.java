package com.develhope.spring.services;

import com.develhope.spring.dao.RestaurantDao;
import com.develhope.spring.mappers.RestaurantMapper;
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

    public RestaurantDto createRestaurant(RestaurantDto resDto)  {
        if(RestaurantValidator.isRestaurantNameValid(resDto.getRestaurantName())){
            RestaurantEntity resEntity = RestaurantMapper.toEntity(resDto);
            RestaurantEntity resEntitySaved = resDao.save(resEntity);
            return RestaurantMapper.toDto(resEntitySaved);
        } else {
            return null
        }
    }

    public RestaurantEntity updateRestaurant(RestaurantEntity res) {
        // TODO Auto-generated method stub

        Optional<RestaurantEntity> opt = resDao.findById(res.getRestaurantId());

        if(opt.isPresent()) {
            return resDao.save(res);
        }
        else
            throw new RestaurantException("Restaurant not found.....");

    }

    // Long id_user, String email, String restaurantName, String restaurantPhoneNumber, AddressEntity address,
    // String description, boolean isDeliveryAvaible, boolean isTakeAwayAvaible, List<ItemEntity> items, List<OperationHoursEntity> operatingHours

    public RestaurantDao updatePatchRestaurant(Long id, RestaurantDto resDto) {
        RestaurantEntity resEntity = RestaurantMapper.toEntity(resDto);
        Optional<RestaurantEntity> optionalResEntityInDB = resDao.findById(id);
        if (optionalResEntityInDB.isPresent()){
            if (resEntity.get != null){
                book.setAuthor(bookPatch.getAuthor());
            }
            if (bookPatch.getTitle() != null){
                book.setTitle(bookPatch.getTitle());
            }
            if (bookPatch.getIsbn() != null){
                book.setIsbn(bookPatch.getIsbn());
            }
            if (bookPatch.isAMasterpiece() != book.isIsAMasterpiece()){
                book.setIsAMasterpiece(bookPatch.isAMasterpiece());
            }
            return updateBook(book);
        } else {
            return ;
        }

    }

    public RestaurantEntity viewRestaurant(Integer resId) throws RestaurantException {
        // TODO Auto-generated method stub
        Optional<RestaurantEntity> opt = resDao.findById(resId);

        if(opt.isPresent()) {
            return opt.get();
        }
        else
            throw new RestaurantException("Restaurant not found.....");


    }


    public List<RestaurantEntity> viewNearByRestaurant(Address address) throws RestaurantException {
        // TODO Auto-generated method stub

        List<RestaurantEntity> rests = resDao.findByAddress(address);


        if(rests.size()>0)
            return rests;
        else
            throw new RestaurantException("Restaurant not found with this address :"+address);
    }

    @Override
    public List<RestaurantEntity> viweRestaurantByItemName(String itemName) throws RestaurantException {
        // TODO Auto-generated method stub




        return null;
    }



}
