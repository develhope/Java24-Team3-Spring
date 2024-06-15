package com.develhope.spring.services;

import com.develhope.spring.daos.UserDetailsDao;
import com.develhope.spring.exceptions.InvalidUserDetailsException;
import com.develhope.spring.mappers.UserDetailsMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.UserDetailsDto;
import com.develhope.spring.models.entities.UserDetailsEntity;
import com.develhope.spring.validators.CustomerValidator;
import com.develhope.spring.validators.UserDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {

    private final UserDetailsDao userDetailsDao;
    private final UserDetailsMapper userDetailsMapper;
    private final UserDetailsValidator userDetailsValidator;

    @Autowired
    public UserDetailsService(UserDetailsDao userDetailsDao, UserDetailsMapper userDetailsMapper, CustomerValidator customerValidator, UserDetailsValidator userDetailsValidator) {
        this.userDetailsDao = userDetailsDao;
        this.userDetailsMapper = userDetailsMapper;
        this.userDetailsValidator = userDetailsValidator;
    }

    /**
     * @param userDetailsDto UserDetailsDto
     * @return a new UserDetails
     */
    public ResponseModel addUserDetails(UserDetailsDto userDetailsDto) {
        try {
            userDetailsValidator.userDetailsValidator(userDetailsDto);
            UserDetailsEntity newUserDetails = this.userDetailsMapper.toEntity(userDetailsDto);
            this.userDetailsDao.saveAndFlush(newUserDetails);
            return new ResponseModel(ResponseCode.B, this.userDetailsMapper.toDTO(newUserDetails));
        } catch (InvalidUserDetailsException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    /**
     * @param id userDetails id
     * @return the userDetails of a single user
     */
    public ResponseModel getUserDetailsById(String id) {
        Optional<UserDetailsEntity> userDetailsFound = this.userDetailsDao.findById(id);
        if (userDetailsFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("UserDetails not found with the selected ID");
        } else {
            return new ResponseModel(ResponseCode.C, this.userDetailsMapper.toDTO(userDetailsFound.get()));
        }
    }

    /**
     * @return all users' UserDetails
     */
    public ResponseModel getAllUsersUserDetails() {
        List<UserDetailsDto> userDetails = this.userDetailsDao.findAll().stream().map(this.userDetailsMapper::toDTO).toList();
        if (userDetails.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No UserDetails were found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, userDetails);
        }
    }

    /**
     * @param phoneNumber String
     * @return userDetails of a single customer
     */
    public ResponseModel getUserDetailsByPhoneNumber(String phoneNumber) {
        Optional<UserDetailsEntity> userDetailsFound = this.userDetailsDao.findUserDetailsByPhoneNumber(phoneNumber);
        if (userDetailsFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No customers were found with the selected parameter");
        } else {
            return new ResponseModel(ResponseCode.E, userDetailsFound);
        }
    }

    /**
     * @param creationDate LocalDate
     * @return all userDetails of customers found
     */
    public ResponseModel getUserDetailsByCreationDate(LocalDate creationDate) {
        List<UserDetailsDto> userDetailsFound = this.userDetailsDao.findUserDetailsByCreationDate(creationDate).stream().map(userDetailsMapper::toDTO).toList();
        if (userDetailsFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No customers were found with the selected parameter");
        } else {
            return new ResponseModel(ResponseCode.E, userDetailsFound);
        }
    }

    /**
     * @param id                 userDetails id
     * @param userDetailsUpdates UserDetailsDto
     * @return userDetails updated
     */
    public ResponseModel updateUserDetails(String id, UserDetailsDto userDetailsUpdates) {
        Optional<UserDetailsEntity> userDetailsToUpdate = this.userDetailsDao.findById(id);
        if (userDetailsToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Customer not found with the selected ID");
        } else if (userDetailsUpdates != null) {
            if (userDetailsUpdates.getName() != null) {
                userDetailsToUpdate.get().setName(userDetailsUpdates.getName());
            }
            if (userDetailsUpdates.getSurname() != null) {
                userDetailsToUpdate.get().setSurname(userDetailsUpdates.getSurname());
            }
            if (userDetailsUpdates.getBirthDate() != null) {
                userDetailsToUpdate.get().setBirthDate(userDetailsUpdates.getBirthDate());
            }
            if (userDetailsUpdates.getPhoneNumber() != null) {
                userDetailsToUpdate.get().setPhoneNumber(userDetailsUpdates.getPhoneNumber());
            }
            if (userDetailsUpdates.getCreationDate() != null) {
                userDetailsToUpdate.get().setCreationDate(userDetailsUpdates.getCreationDate());
            }
            if (userDetailsUpdates.getUpdateDate() != null) {
                userDetailsToUpdate.get().setUpdateDate(userDetailsUpdates.getUpdateDate());
            }
            return new ResponseModel(ResponseCode.G, this.userDetailsMapper.toDTO(this.userDetailsDao.saveAndFlush(userDetailsToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param id userDetails id
     */
    public ResponseModel deleteUserDetailsById(String id) {
        if (!this.userDetailsDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("UserDetails not found with the selected ID");
        }
        this.userDetailsDao.deleteById(id);
        return new ResponseModel(ResponseCode.H).addMessageDetails("UserDetails successfully deleted");
    }

    /**
     * delete all users' UserDetails
     */
    public ResponseModel deleteAllUserDetails() {
        this.userDetailsDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All users' UserDetails have been deleted");
    }

}
