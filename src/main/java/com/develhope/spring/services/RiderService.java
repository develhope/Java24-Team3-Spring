package com.develhope.spring.services;

import com.develhope.spring.daos.RiderDao;
import com.develhope.spring.exceptions.InvalidRiderException;
import com.develhope.spring.mappers.RiderMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.RiderDto;
import com.develhope.spring.models.entities.RiderEntity;
import com.develhope.spring.validators.RiderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiderService {

    private final RiderDao dao;
    private final RiderMapper mapper;
    private final RiderValidator validator;

    @Autowired
    public RiderService(RiderDao dao, RiderMapper mapper, RiderValidator validator) {
        this.dao = dao;
        this.mapper = mapper;
        this.validator = validator;
    }

    // CREATE

    public ResponseModel createRider(RiderDto riderDto) {
        try {
            validator.validateRider(riderDto);
            RiderEntity newRider = this.mapper.toEntity(riderDto);
            this.dao.saveAndFlush(newRider);
            return new ResponseModel(ResponseCode.B, this.mapper.toDto(newRider));
        } catch (InvalidRiderException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    // READ

    public ResponseModel getAll() {
        List<RiderDto> riders = this.dao.findAll().stream().map(mapper::toDto).toList();
        if (riders.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no riders found.");
        } else {
            return new ResponseModel(ResponseCode.E, riders);
        }
    }

    public ResponseModel getById(String id) {
        Optional<RiderEntity> riderFound = this.dao.findById(id);
        if (riderFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("rider ID not found.");
        } else {
            return new ResponseModel(ResponseCode.C, this.mapper.toDto(riderFound.get()));
        }
    }

    public ResponseModel getByEmail(String email) {
        Optional<RiderEntity> rider = this.dao.findByEmail(email);
        if (rider.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no riders associated to this e-mail found.");
        } else {
            return new ResponseModel(ResponseCode.C, this.mapper.toDto(rider.get()));
        }
    }

    public ResponseModel getByDeletedStatus(Boolean isDeleted) {
        List<RiderDto> riders = this.dao.findByIsDeleted(isDeleted).stream().map(mapper::toDto).toList();
        if (riders.isEmpty()) {
            String messageDetails;
            if (isDeleted) {
                messageDetails = "no deleted riders found.";
            } else {
                messageDetails = "no registered riders found.";
            }

            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, riders);
        }
    }

    public ResponseModel getByVerifiedStatus(Boolean isVerified) {
        List<RiderDto> riders = this.dao.findByIsVerified(isVerified).stream().map(mapper::toDto).toList();
        if (riders.isEmpty()) {
            String messageDetails;
            if (isVerified) {
                messageDetails = "no verified riders found.";
            } else {
                messageDetails = "no riders that are not yet verified found.";
            }

            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, riders);
        }
    }

    public ResponseModel getByAvailableStatus(Boolean available) {
        List<RiderDto> riders = this.dao.findByAvailable(available).stream().map(mapper::toDto).toList();
        if (riders.isEmpty()) {
            String messageDetails;
            if (available) {
                messageDetails = "no available riders found.";
            } else {
                messageDetails = "no idle riders found.";
            }

            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, riders);
        }
    }

    /*
    public ResponseModel getByAvailabilityAndDistance(double[] coordinates, double maximumDistance) {
        List<RiderDto> availableRiders = this.dao.findByAvailable(true).stream().map(mapper::toDto).toList();
        if (availableRiders.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No riders found.");
        } else {

            BigDecimal distance;
            List<RiderDto> ridersInRange = new ArrayList<RiderDto>();

            for (RiderDto rider : availableRiders) {
                distance = DistanceCalculator.calculateDistance(BigDecimal.valueOf(coordinates[0]), BigDecimal.valueOf(coordinates[1]), BigDecimal.valueOf(rider.getStartingPosition()[0]), BigDecimal.valueOf(rider.getStartingPosition()[1]), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
                if (distance.compareTo(BigDecimal.valueOf(maximumDistance)) <= 0) {
                    ridersInRange.add(rider);
                }
            }

            return new ResponseModel(ResponseCode.E, ridersInRange);
        }
    }
    */

    // UPDATE

    public ResponseModel updateDetails(String id, RiderDto updatedRider) {

        Optional<RiderEntity> riderToUpdate = this.dao.findById(id);

        if (riderToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("rider ID not found.");
        } else if (updatedRider != null) {

            RiderEntity updatedRiderEntity = this.mapper.toEntity(updatedRider);

            if (updatedRider.getEmail() != null || !updatedRider.getEmail().isEmpty()) {
                riderToUpdate.get().setEmail(updatedRiderEntity.getEmail());
            }

            if (updatedRider.getPassword() != null) {
                riderToUpdate.get().setPassword(updatedRiderEntity.getPassword());
            }

            if (updatedRider.getIsDeleted() != null) {
                riderToUpdate.get().setIsDeleted(updatedRiderEntity.getIsDeleted());
            }

            if (updatedRider.getIsVerified() != null) {
                riderToUpdate.get().setIsVerified(updatedRiderEntity.getIsVerified());
            }

            if (updatedRider.getUserDetails() != null) {
                riderToUpdate.get().setUserDetails(updatedRiderEntity.getUserDetails());
            }

            return new ResponseModel(ResponseCode.G, this.mapper.toDto(this.dao.saveAndFlush(riderToUpdate.get())));
        }

        return new ResponseModel(ResponseCode.A).addMessageDetails("update failed (null body).");
    }

    // DELETE

    public ResponseModel deleteById(String id) {
        if (!this.dao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("rider ID not found.");
        } else {
            this.dao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("rider successfully deleted.");
        }
    }

    public ResponseModel deleteAll() {
        this.dao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("all riders have been successfully deleted.");
    }

}