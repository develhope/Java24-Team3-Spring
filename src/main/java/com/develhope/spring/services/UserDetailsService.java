package com.develhope.spring.services;

import com.develhope.spring.daos.UserDetailsDao;
import com.develhope.spring.mappers.UserDetailsMapper;
import com.develhope.spring.models.dtos.UserDetailsDto;
import com.develhope.spring.models.entities.UserDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {

    private final UserDetailsDao userDetailsDao;
    private final UserDetailsMapper userDetailsMapper;

    @Autowired
    public UserDetailsService(UserDetailsDao userDetailsDao, UserDetailsMapper userDetailsMapper) {
        this.userDetailsDao = userDetailsDao;
        this.userDetailsMapper = userDetailsMapper;
    }

    public UserDetailsDto addUserDetails(UserDetailsDto userDetailsDto) {
        UserDetailsEntity newUserDetails = this.userDetailsMapper.toEntity(userDetailsDto);
        this.userDetailsDao.saveAndFlush(newUserDetails);
        return this.userDetailsMapper.toDTO(newUserDetails);
    }

    public UserDetailsDto getUserDetailsById(Long id) {
        Optional<UserDetailsEntity> userDetailsFound = this.userDetailsDao.findById(id);
        if (userDetailsFound.isEmpty()) {
            return new UserDetailsDto();//DA CAMBIARE
        } else {
            return this.userDetailsMapper.toDTO(userDetailsFound.get());
        }
    }

    public List<UserDetailsDto> getAllUsersUserDetails() {
        List<UserDetailsDto> userDetails = this.userDetailsDao.findAll().stream().map(userDetailsMapper::toDTO).toList();
        if (userDetails.isEmpty()) {
            return new ArrayList<>();
        } else {
            return userDetails;
        }
    }

    public List<UserDetailsDto> getUserDetailsByPhoneNumber(String phoneNumber) {
        List<UserDetailsDto> userDetailsFound = this.userDetailsDao.findUserDetailsByPhoneNumber(phoneNumber).stream().map(userDetailsMapper::toDTO).toList();
        if (userDetailsFound.isEmpty()) {
            return new ArrayList<>();
        } else {
            return userDetailsFound;
        }
    }

    public List<UserDetailsDto> getUserDetailsByCreationDate(LocalDate creationDate) {
        List<UserDetailsDto> userDetailsFound = this.userDetailsDao.findUserDetailsByCreationDate(creationDate).stream().map(userDetailsMapper::toDTO).toList();
        if (userDetailsFound.isEmpty()) {
            return new ArrayList<>();
        } else {
            return userDetailsFound;
        }
    }

    public UserDetailsDto updateUserDetails(Long id, UserDetailsDto userDetailsUpdates) {
        Optional<UserDetailsEntity> userDetailsToUpdate = this.userDetailsDao.findById(id);
        if (userDetailsToUpdate.isEmpty()) {
            return new UserDetailsDto();
        } else {
            userDetailsToUpdate.get().setName(userDetailsUpdates.getName());
            userDetailsToUpdate.get().setSurname(userDetailsUpdates.getSurname());
            userDetailsToUpdate.get().setBirthDate(userDetailsUpdates.getBirthDate());
            userDetailsToUpdate.get().setPhoneNumber(userDetailsUpdates.getPhoneNumber());
            userDetailsToUpdate.get().setCreationDate(userDetailsUpdates.getCreationDate());
            userDetailsToUpdate.get().setUpdateDate(userDetailsUpdates.getUpdateDate());

            return this.userDetailsMapper.toDTO(this.userDetailsDao.saveAndFlush(userDetailsToUpdate.get()));
        }
    }

    public void deleteUserDetailsById(Long id) {
//        if(!this.userDetailsDao.existsById(id)) {
//            throw exception;// DA CAMBIARE
//        }
        this.userDetailsDao.deleteById(id);
    }

    public void deleteAllUserDetails() {
        this.userDetailsDao.deleteAll();
    }

}
