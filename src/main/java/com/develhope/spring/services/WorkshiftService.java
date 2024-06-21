package com.develhope.spring.services;

import com.develhope.spring.daos.WorkshiftDao;
import com.develhope.spring.exceptions.InvalidWorkshiftException;
import com.develhope.spring.mappers.WorkshiftMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.WorkshiftDto;
import com.develhope.spring.models.entities.WorkshiftEntity;
import com.develhope.spring.validators.WorkshiftValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkshiftService {

    private WorkshiftDao dao;
    private WorkshiftMapper mapper;
    private WorkshiftValidator validator;

    @Autowired
    public WorkshiftService(WorkshiftDao workshiftDao, WorkshiftMapper workshiftMapper, WorkshiftValidator workshiftValidator) {
        this.dao = workshiftDao;
        this.mapper = workshiftMapper;
        this.validator = workshiftValidator;
    }

    // CREATE

    public ResponseModel create(WorkshiftDto workshiftDto) {
        try {
            validator.validateWorkshift(workshiftDto);
            WorkshiftEntity newWorkshift = this.mapper.toEntity(workshiftDto);
            this.dao.saveAndFlush(newWorkshift);
            return new ResponseModel(ResponseCode.B, mapper.toDto(newWorkshift));
        } catch (InvalidWorkshiftException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    // READ

    public ResponseModel getAll() {
        List<WorkshiftDto> workshifts = this.dao.findAll().stream().map(mapper::toDto).toList();
        if (workshifts.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no workshifts found.");
        } else {
            return new ResponseModel(ResponseCode.E, workshifts);
        }
    }

    public ResponseModel getById(String id) {
        Optional<WorkshiftEntity> workshift = this.dao.findById(id);
        if (workshift.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("workshift ID not found.");
        } else {
            return new ResponseModel(ResponseCode.C, mapper.toDto(workshift.get()));
        }
    }

    public ResponseModel getByDayOfWeek(DayOfWeek dayOfWeek) {
        List<WorkshiftEntity> workshifts = this.dao.findByDayOfWeek(dayOfWeek);
        if (workshifts.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no workshifts on " + dayOfWeek.toString() + " found.");
        } else {
            return new ResponseModel(ResponseCode.C, workshifts);
        }
    }

    public ResponseModel getByStartingBefore(LocalTime hour) {
        List<WorkshiftEntity> workshifts = this.dao.findByStartBefore(hour);
        if (workshifts.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no workshifts starting before " + hour.toString() + " found.");
        } else {
            return new ResponseModel(ResponseCode.C, workshifts);
        }
    }

    public ResponseModel getByStartingAfter(LocalTime hour) {
        List<WorkshiftEntity> workshifts = this.dao.findByStartAfter(hour);
        if (workshifts.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no workshifts starting after " + hour.toString() + " found.");
        } else {
            return new ResponseModel(ResponseCode.C, workshifts);
        }
    }

    public ResponseModel getByStartingBetween(LocalTime minHour, LocalTime maxHour) {
        if (minHour.equals(maxHour)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("the start and the end of the time range cannot be the same.");
        } else {
            if (minHour.isAfter(maxHour)) {
                LocalTime shift = maxHour;
                maxHour = minHour;
                minHour = shift;
            }

            List<WorkshiftEntity> workshifts = this.dao.findByStartBetween(minHour, maxHour);
            if (workshifts.isEmpty()) {
                return new ResponseModel(ResponseCode.D).addMessageDetails("no workshifts starting betweeen " + minHour.toString() + " and " + maxHour.toString() + " found.");
            } else {
                return new ResponseModel(ResponseCode.C, workshifts);
            }
        }
    }

    public ResponseModel getByEndingBefore(LocalTime hour) {
        List<WorkshiftEntity> workshifts = this.dao.findByEndBefore(hour);
        if (workshifts.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no workshifts ending before " + hour.toString() + " found.");
        } else {
            return new ResponseModel(ResponseCode.C, workshifts);
        }
    }

    public ResponseModel getByEndingAfter(LocalTime hour) {
        List<WorkshiftEntity> workshifts = this.dao.findByEndAfter(hour);
        if (workshifts.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no workshifts ending after " + hour.toString() + " found.");
        } else {
            return new ResponseModel(ResponseCode.C, workshifts);
        }
    }

    public ResponseModel getByEndingBetween(LocalTime minHour, LocalTime maxHour) {
        if (minHour.equals(maxHour)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("the start and the end of the time range cannot be the same.");
        } else {
            if (minHour.isAfter(maxHour)) {
                LocalTime shift = maxHour;
                maxHour = minHour;
                minHour = shift;
            }

            List<WorkshiftEntity> workshifts = this.dao.findByEndBetween(minHour, maxHour);
            if (workshifts.isEmpty()) {
                return new ResponseModel(ResponseCode.D).addMessageDetails("no workshifts ending betweeen " + minHour.toString() + " and " + maxHour.toString() + " found.");
            } else {
                return new ResponseModel(ResponseCode.C, workshifts);
            }
        }
    }

    // PUT

    public ResponseModel update(String id, WorkshiftDto updated) {
        Optional<WorkshiftEntity> toUpdate = this.dao.findById(id);

        if (toUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("workshift ID not found.");
        } else if (updated != null) {
            WorkshiftEntity update = this.mapper.toEntity(updated);

            if (updated.getDayOfWeek() != null) {
                toUpdate.get().setDayOfWeek(update.getDayOfWeek());
            }

            if (updated.getStart() != null) {
                toUpdate.get().setEnd(update.getEnd());
            }

            if (updated.getEnd() != null) {
                toUpdate.get().setStart(update.getStart());
            }

            return new ResponseModel(ResponseCode.G, this.mapper.toDto(this.dao.saveAndFlush(toUpdate.get())));
        }

        return new ResponseModel(ResponseCode.A).addMessageDetails("update failed (null body).");
    }

    // DELETE

    public ResponseModel deleteById(String id) {
        if (!this.dao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("workshift ID not found.");
        } else {
            this.dao.findById(id).get().setRider(null);
            this.dao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("workshift successfully deleted.");
        }
    }

    public ResponseModel deleteAll() {
        List<WorkshiftEntity> workshifts = this.dao.findAll();

        for (WorkshiftEntity workshift : workshifts) {
            workshift.getRider().setWorkshifts(null);
        }

        this.dao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("all workshifts have been successfully deleted.");
    }

}