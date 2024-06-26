package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidWorkshiftException;
import com.develhope.spring.models.dtos.WorkshiftDto;
import org.springframework.stereotype.Component;

import static com.develhope.spring.utils.TimeLimits.*;

@Component
public class WorkshiftValidator {

    public void validateWorkshift(WorkshiftDto workshiftDto) throws InvalidWorkshiftException {
        if (workshiftDto.getDayOfWeek() == null) {
            throw new InvalidWorkshiftException("A workshift must be in a day of the week.");
        }

        if (workshiftDto.getStart() == null) {
            throw new InvalidWorkshiftException("A workshift must have a starting hour.");
        }

        if (workshiftDto.getEnd() == null) {
            throw new InvalidWorkshiftException("A workshift must have an ending hour.");
        }

        if (!isWithinTheLimits(workshiftDto.getStart(), workshiftDto.getEnd())) {
            throw new InvalidWorkshiftException("A workshift can only start before " + getStartingHourLimit().toString() + " and end after " + getEndingHourLimit().toString() + ".");
        }
    }

}