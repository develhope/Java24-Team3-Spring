package com.develhope.spring.services;

import com.develhope.spring.daos.OwnerDao;
import com.develhope.spring.daos.UserDetailsDao;
import com.develhope.spring.mappers.OwnerMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.OwnerDto;
import com.develhope.spring.models.entities.OwnerEntity;
import com.develhope.spring.validators.ContactValidator;
import com.develhope.spring.validators.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerDao ownerDao;
    private final OwnerMapper ownerMapper;
    private final ContactValidator contactValidator;
    private final IdValidator idValidator;
    private final UserDetailsDao userDetailsDao;
    

    public OwnerService(OwnerDao ownerDao, OwnerMapper ownerMapper, ContactValidator contactValidator, IdValidator idValidator, UserDetailsDao userDetailsDao) {
        this.ownerDao = ownerDao;
        this.ownerMapper = ownerMapper;
        this.contactValidator = contactValidator;
        this.idValidator = idValidator;
        this.userDetailsDao = userDetailsDao;
    }

    @Autowired


    /**
     * @param ownerDto OwnerDto
     * @return a new Owner
     */
    public ResponseModel addOwner(OwnerDto ownerDto) {
        try {
            // validate
            idValidator.noId(ownerDto.getId());
            idValidator.noId(ownerDto.getUserDetailsDto().getId());
            contactValidator.validateEmail(ownerDto.getEmail());
            contactValidator.validatePassword(ownerDto.getPassword());
            contactValidator.validatePhoneNumber(ownerDto.getUserDetailsDto().getPhoneNumber());


            OwnerEntity newOwner = ownerMapper.toEntity(ownerDto);
            OwnerEntity newOwnerEntity = ownerDao.saveAndFlush(newOwner);
            return new ResponseModel(ResponseCode.B, ownerMapper.toDto(newOwnerEntity));
        } catch (Exception e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }

    }

    /**
     * @param id owner id
     * @return a single owner
     */
    public ResponseModel getOwnerById(String id) {
        Optional<OwnerEntity> ownerFound = this.ownerDao.findById(id);
        if (ownerFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Owner not found with the selected ID");
        } else {
            return new ResponseModel(ResponseCode.C, ownerMapper.toDto(ownerFound.get()));
        }
    }

    /**
     * @return List of all owners
     */
    public ResponseModel getAllOwners() {
        List<OwnerDto> owners = ownerDao.findAll().stream().map(ownerMapper::toDto).toList();
        if (owners.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No owners were found");
        } else {
            return new ResponseModel(ResponseCode.E, owners);
        }
    }

    /**
     * @param email String
     * @return a single owner
     */
    public ResponseModel getOwnerByEmail(String email) {
        Optional<OwnerEntity> ownerFound = this.ownerDao.findOwnerByEmail(email);
        if (ownerFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Owner not found with the selected email");
        } else {
            return new ResponseModel(ResponseCode.C, this.ownerMapper.toDto(ownerFound.get()));
        }
    }

    /**
     * @param isDeleted Boolean
     * @return all owners with the selected deleted status
     */
    public ResponseModel getOwnerByDeletedStatus(Boolean isDeleted) {
        List<OwnerDto> owners = this.ownerDao.findOwnerByIsDeleted(isDeleted).stream().map(ownerMapper::toDto).toList();
        if (owners.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No owners were found with the selected parameter");
        } else {
            return new ResponseModel(ResponseCode.E, owners);
        }
    }

    /**
     * @param isVerified Boolean
     * @return all owners with the selected verified status
     */
    public ResponseModel getOwnersByVerifiedStatus(Boolean isVerified) {
        List<OwnerDto> owners = this.ownerDao.findOwnerByIsVerified(isVerified).stream().map(ownerMapper::toDto).toList();
        if (owners.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No owners were found with the selected parameter");
        } else {
            return new ResponseModel(ResponseCode.E, owners);
        }
    }

    /**
     * @param id           owner id
     * @param ownerUpdates OwnerDto
     * @return a owner updated
     */
    public ResponseModel updateOwner(String id, OwnerDto ownerUpdates) {
        Optional<OwnerEntity> ownerToUpdate = this.ownerDao.findById(id);
        if (ownerToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Owner not found with the selected Id.");
        } else if (ownerUpdates != null) {
            OwnerEntity ownerEntityUpdates = this.ownerMapper.toEntity(ownerUpdates);
            if (ownerUpdates.getEmail() != null) {
                ownerToUpdate.get().setEmail(ownerUpdates.getEmail());
            }
            if (ownerUpdates.getPassword() != null) {
                ownerToUpdate.get().setPassword(ownerUpdates.getPassword());
            }
            if (ownerUpdates.getIsDeleted() != null) {
                ownerToUpdate.get().setIsDeleted(ownerUpdates.getIsDeleted());
            }
            if (ownerUpdates.getIsVerified() != null) {
                ownerToUpdate.get().setIsVerified(ownerUpdates.getIsVerified());
            }
            if (ownerUpdates.getUserDetailsDto() != null) {
                //UserDetailsEntity updatedUserDetails = userDetailsDao.save(ownerEntityUpdates.getUserDetails());
                ownerToUpdate.get().setUserDetailsEntity(ownerEntityUpdates.getUserDetails());
            }
            return new ResponseModel(ResponseCode.G, ownerMapper.toDto(ownerDao.saveAndFlush(ownerToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param id       owner id
     * @param ownerDto OwnerDto
     * @return owner with password updated
     */
    public ResponseModel updatePassword(String id, OwnerDto ownerDto) {
        Optional<OwnerEntity> ownerToUpdate = this.ownerDao.findById(id);
        if (ownerToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Owner not found with the selected ID");
        } else if (ownerDto != null) {
            if (ownerDto.getPassword() != null) {
                ownerToUpdate.get().setPassword(ownerDto.getPassword());
                return new ResponseModel(ResponseCode.G, ownerMapper.toDto(this.ownerDao.saveAndFlush(ownerToUpdate.get())));
            }
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param id owner id
     */
    public ResponseModel deleteOwner(String id) {
        if (!this.ownerDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Owner not found with the selected ID");
        } else {
            this.ownerDao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("Owner eliminated");
        }
    }

    public ResponseModel deleteAllOwners() {
        this.ownerDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All owners eliminated");
    }


}
