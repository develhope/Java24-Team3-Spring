package com.develhope.spring.services;

import com.develhope.spring.daos.RiderDao;
import com.develhope.spring.exceptions.InvalidRiderException;
import com.develhope.spring.mappers.RiderMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.RiderDto;
import com.develhope.spring.models.entities.RiderEntity;
import com.develhope.spring.utils.DistanceCalculator;
import com.develhope.spring.validators.RiderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RiderService {

    private final RiderDao riderDao;
    private final RiderMapper riderMapper;
    private final RiderValidator riderValidator;

    @Autowired
    public RiderService(RiderDao riderDao, RiderMapper riderMapper, RiderValidator riderValidator) {
        this.riderDao = riderDao;
        this.riderMapper = riderMapper;
        this.riderValidator = riderValidator;
    }

    // CREATE

    public ResponseModel addRider(RiderDto riderDto) {
        try {
            riderValidator.validateRider(riderDto);
            RiderEntity newRider = this.riderMapper.toEntity(riderDto);
            this.riderDao.saveAndFlush(newRider);
            return new ResponseModel(ResponseCode.B, this.riderMapper.toDTO(newRider));
        } catch (InvalidRiderException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    // READ

    public ResponseModel getAll() {
        List<RiderDto> riders = this.riderDao.findAll().stream().map(riderMapper::toDTO).toList();
        if (riders.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No riders found.");
        } else {
            return new ResponseModel(ResponseCode.E, riders);
        }
    }

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

    public ResponseModel getByAvailableStatus(Boolean isAvailable) {
        List<RiderDto> riders = this.riderDao.findByAvailableStatus(isAvailable).stream().map(riderMapper::toDTO).toList();
        if (riders.isEmpty()) {
            String messageDetails;
            if (isAvailable) {
                messageDetails = "No available riders found.";
            } else {
                messageDetails = "No riders that are not available found.";
            }

            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, riders);
        }
    }

    public ResponseModel getByAvailabilityAndDistance(BigDecimal[] coordinates, int maximumDistance) {
        List<RiderDto> availableRiders = this.riderDao.findByAvailableStatus(true).stream().map(riderMapper::toDTO).toList();
        if (availableRiders.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No riders found.");
        } else {

            int distance;
            List<RiderDto> ridersInRange = new ArrayList<RiderDto>();

            for (RiderDto rider : availableRiders) {
                distance = DistanceCalculator.calculateDistance(coordinates[0], coordinates[1], rider.getStartingPosition()[0], rider.getStartingPosition()[1], 0.0, 0.0);
                if (distance <= maximumDistance) {
                    ridersInRange.add(rider);
                }
            }

            return new ResponseModel(ResponseCode.E, ridersInRange);
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

            if (updatedRider.getIsAvailable() != null) {
                riderToUpdate.get().setIsAvailable(updatedRider.getIsAvailable());
            }

            if (updatedRider.getStartingPosition() != null) {
                riderToUpdate.get().setStartingPosition(updatedRider.getStartingPosition());
            }

            if (updatedRider.getCurrentPosition() != null) {
                riderToUpdate.get().setCurrentPosition(updatedRider.getCurrentPosition());
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

    public ResponseModel deleteAll() {
        this.riderDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All riders deleted.");
    }

}