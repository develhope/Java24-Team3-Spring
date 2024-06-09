package com.develhope.spring.services;

import com.develhope.spring.daos.RiderDao;
import com.develhope.spring.mappers.RiderMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.RiderDto;
import com.develhope.spring.models.entities.RiderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiderService {

    private final RiderDao riderDao;
    private final RiderMapper riderMapper;

    @Autowired
    public RiderService(RiderDao riderDao, RiderMapper riderMapper) {
        this.riderDao = riderDao;
        this.riderMapper = riderMapper;
    }

    // CREATE

    public ResponseModel addRider(RiderDto riderDto) {
        RiderEntity newRider = this.riderMapper.toEntity(riderDto);
        this.riderDao.saveAndFlush(newRider);
        return new ResponseModel(ResponseCode.B, this.riderMapper.toDTO(newRider));
    }

    // READ

    public ResponseModel getById(String id) {
        Optional<RiderEntity> riderFound = this.riderDao.findById(id);
        if (riderFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Rider ID not found.");
        } else {
            return new ResponseModel(ResponseCode.C, this.riderMapper.toDTO(riderFound.get()));
        }
    }

    public ResponseModel getByEmail(String email) {
        Optional<RiderEntity> rider = this.riderDao.findByEmail(email);
        if (rider.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Rider e-mail not found.");
        } else {
            return new ResponseModel(ResponseCode.C, this.riderMapper.toDTO(rider.get()));
        }
    }

    public ResponseModel getAll() {
        List<RiderDto> riders = this.riderDao.findAll().stream().map(riderMapper::toDTO).toList();
        if (riders.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No riders found.");
        } else {
            return new ResponseModel(ResponseCode.E, riders);
        }
    }

    public ResponseModel getByDeletedStatus(Boolean isDeleted) {
        List<RiderDto> riders = this.riderDao.findByDeletedStatus(isDeleted).stream().map(riderMapper::toDTO).toList();
        if (riders.isEmpty()) {
            String messageDetails;
            if (isDeleted) {
                messageDetails = "No deleted riders found.";
            } else {
                messageDetails = "No active riders found.";
            }

            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, riders);
        }
    }

    public ResponseModel getByVerifiedStatus(Boolean isVerified) {
        List<RiderDto> riders = this.riderDao.findByVerifiedStatus(isVerified).stream().map(riderMapper::toDTO).toList();
        if (riders.isEmpty()) {
            String messageDetails;
            if (isVerified) {
                messageDetails = "No verified riders found.";
            } else {
                messageDetails = "No riders that are not yet verified found.";
            }

            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, riders);
        }
    }

    // UPDATE

    public ResponseModel update(String id, RiderDto updatedRider) {

        Optional<RiderEntity> riderToUpdate = this.riderDao.findById(id);

        if (riderToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Rider ID not found.");
        } else if (updatedRider != null) {

            if (updatedRider.getEmail() != null) {
                riderToUpdate.get().setEmail(updatedRider.getEmail());
            }

            if (updatedRider.getPassword() != null) {
                riderToUpdate.get().setPassword(updatedRider.getPassword());
            }

            if (updatedRider.getIsDeleted() != null) {
                riderToUpdate.get().setIsDeleted(updatedRider.getIsDeleted());
            }

            if (updatedRider.getIsVerified() != null) {
                riderToUpdate.get().setIsVerified(updatedRider.getIsVerified());
            }

            if (updatedRider.getUserDetails() != null) {
                riderToUpdate.get().setUserDetailsEntity(updatedRider.getUserDetails());
            }

            return new ResponseModel(ResponseCode.G, this.riderMapper.toDTO(this.riderDao.saveAndFlush(riderToUpdate.get())));
        }

        return new ResponseModel(ResponseCode.A).addMessageDetails("Update failed (null body).");
    }

    // DELETE

    public ResponseModel delete(String id) {
        if (!this.riderDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Rider ID not found.");
        } else {
            this.riderDao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("Rider deleted successfully.");
        }
    }

}
