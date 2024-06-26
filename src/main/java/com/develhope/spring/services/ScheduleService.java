package com.develhope.spring.services;

import com.develhope.spring.daos.ScheduleDao;
import com.develhope.spring.exceptions.InvalidScheduleException;
import com.develhope.spring.mappers.ScheduleMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ScheduleDto;
import com.develhope.spring.models.entities.ScheduleEntity;
import com.develhope.spring.validators.ScheduleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private ScheduleDao dao;
    private ScheduleMapper mapper;
    private ScheduleValidator validator;

    @Autowired
    public ScheduleService(ScheduleDao scheduleDao, ScheduleMapper scheduleMapper, ScheduleValidator scheduleValidator) {
        this.dao = scheduleDao;
        this.mapper = scheduleMapper;
        this.validator = scheduleValidator;
    }

    // CREATE

    public ResponseModel create(ScheduleDto scheduleDto) {
        try {
            validator.validateSchedule(scheduleDto);
            ScheduleEntity newSchedule = this.mapper.toEntity(scheduleDto);
            this.dao.saveAndFlush(newSchedule);
            return new ResponseModel(ResponseCode.B, mapper.toDto(newSchedule));
        } catch (InvalidScheduleException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    // READ

    public ResponseModel getAll() {
        List<ScheduleDto> schedules = this.dao.findAll().stream().map(mapper::toDto).toList();
        if (schedules.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No schedules found.");
        } else {
            return new ResponseModel(ResponseCode.E, schedules);
        }
    }

    public ResponseModel getById(String id) {
        Optional<ScheduleEntity> schedule = this.dao.findById(id);
        if (schedule.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Schedule empty or not found.");
        } else {
            return new ResponseModel(ResponseCode.C, mapper.toDto(schedule.get()));
        }
    }

    public ResponseModel getByDayOfWeek(DayOfWeek dayOfWeek) {
        List<ScheduleEntity> schedules = this.dao.findByDayOfWeek(dayOfWeek);
        if (schedules.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No schedules on " + dayOfWeek.toString() + " found.");
        } else {
            return new ResponseModel(ResponseCode.C, schedules);
        }
    }

    public ResponseModel getByStartingBefore(LocalTime hour) {
        List<ScheduleEntity> schedules = this.dao.findByStartBefore(hour);
        if (schedules.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No schedules starting before " + hour.toString() + " found.");
        } else {
            return new ResponseModel(ResponseCode.C, schedules);
        }
    }

    public ResponseModel getByStartingAfter(LocalTime hour) {
        List<ScheduleEntity> schedules = this.dao.findByStartAfter(hour);
        if (schedules.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No schedules starting after " + hour.toString() + " found.");
        } else {
            return new ResponseModel(ResponseCode.C, schedules);
        }
    }

    public ResponseModel getByStartingBetween(LocalTime minHour, LocalTime maxHour) {
        if (minHour.equals(maxHour)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("The start and the end of the time range cannot be the same.");
        } else {
            if (minHour.isAfter(maxHour)) {
                LocalTime shift = maxHour;
                maxHour = minHour;
                minHour = shift;
            }

            List<ScheduleEntity> schedules = this.dao.findByStartBetween(minHour, maxHour);
            if (schedules.isEmpty()) {
                return new ResponseModel(ResponseCode.D).addMessageDetails("No schedules starting betweeen " + minHour.toString() + " and " + maxHour.toString() + " found.");
            } else {
                return new ResponseModel(ResponseCode.C, schedules);
            }
        }
    }

    public ResponseModel getByEndingBefore(LocalTime hour) {
        List<ScheduleEntity> schedules = this.dao.findByEndBefore(hour);
        if (schedules.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No schedules ending before " + hour.toString() + " found.");
        } else {
            return new ResponseModel(ResponseCode.C, schedules);
        }
    }

    public ResponseModel getByEndingAfter(LocalTime hour) {
        List<ScheduleEntity> schedules = this.dao.findByEndAfter(hour);
        if (schedules.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No schedules ending after " + hour.toString() + " found.");
        } else {
            return new ResponseModel(ResponseCode.C, schedules);
        }
    }

    public ResponseModel getByEndingBetween(LocalTime minHour, LocalTime maxHour) {
        if (minHour.equals(maxHour)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("The start and the end of the time range cannot be the same.");
        } else {
            if (minHour.isAfter(maxHour)) {
                LocalTime shift = maxHour;
                maxHour = minHour;
                minHour = shift;
            }

            List<ScheduleEntity> schedules = this.dao.findByEndBetween(minHour, maxHour);
            if (schedules.isEmpty()) {
                return new ResponseModel(ResponseCode.D).addMessageDetails("No schedules ending betweeen " + minHour.toString() + " and " + maxHour.toString() + " found.");
            } else {
                return new ResponseModel(ResponseCode.C, schedules);
            }
        }
    }

    // PUT

    public ResponseModel update(String id, ScheduleDto updated) {
        Optional<ScheduleEntity> toUpdate = this.dao.findById(id);

        if (toUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Schedule empty or not found.");
        } else if (updated != null) {
            ScheduleEntity update = this.mapper.toEntity(updated);

            if (updated.getDayOfWeek() != null) {
                toUpdate.get().setDayOfWeek(update.getDayOfWeek());
            }

            if (updated.getStart() != null) {
                toUpdate.get().setStart(update.getStart());
            }

            if (updated.getEnd() != null) {
                toUpdate.get().setEnd(update.getEnd());
            }

            return new ResponseModel(ResponseCode.G, this.mapper.toDto(this.dao.saveAndFlush(toUpdate.get())));
        }

        return new ResponseModel(ResponseCode.A).addMessageDetails("Update failed (null body).");
    }

    // DELETE

    public ResponseModel deleteById(String id) {
        if (!this.dao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Schedule not found.");
        } else {
            this.dao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("Schedule deleted.");
        }
    }

    public ResponseModel deleteAll() {
        this.dao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All schedules have been deleted");
    }

}