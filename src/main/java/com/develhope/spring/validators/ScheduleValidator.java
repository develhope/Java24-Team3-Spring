package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidScheduleException;
import com.develhope.spring.models.dtos.ScheduleDto;
import org.springframework.stereotype.Component;

@Component
public class ScheduleValidator {

    public void validateSchedule(ScheduleDto scheduleDto) throws InvalidScheduleException {
        if (scheduleDto.getDayOfWeek() == null) {
            throw new InvalidScheduleException("A schedule must be in a day of the week.");
        }

        if (scheduleDto.getStart() == null) {
            throw new InvalidScheduleException("A schedule must have a starting hour.");
        }

        if (scheduleDto.getEnd() == null) {
            throw new InvalidScheduleException("A schedule must have an ending hour.");
        }
    }

}