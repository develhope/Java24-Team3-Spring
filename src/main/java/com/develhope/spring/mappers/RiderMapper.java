package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.RiderDto;
import com.develhope.spring.models.entities.RiderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RiderMapper {

    private final UserDetailsMapper userDetailsMapper;
    private final WorkshiftMapper workshiftMapper;

    @Autowired
    public RiderMapper(UserDetailsMapper userDetailsMapper, WorkshiftMapper workshiftMapper) {
        this.userDetailsMapper = userDetailsMapper;
        this.workshiftMapper = workshiftMapper;
    }

    public RiderEntity toEntity(RiderDto riderDto) {
        if (riderDto == null) {
            return null;
        }

        RiderEntity riderEntity = new RiderEntity();

        riderEntity.setId(riderDto.getId());
        riderEntity.setEmail(riderDto.getEmail());
        riderEntity.setPassword(riderDto.getPassword());
        riderEntity.setIsDeleted(riderDto.getIsDeleted());
        riderEntity.setIsVerified(riderDto.getIsVerified());
        riderEntity.setUserDetails(this.userDetailsMapper.toEntity(riderDto.getUserDetails()));

        riderEntity.setStartingPosition(riderDto.getCurrentPosition());
        riderEntity.setCurrentPosition(riderDto.getCurrentPosition());
        riderEntity.setAvailable(riderDto.getAvailable());

        if (riderDto.getWorkshifts() != null) {
            riderEntity.setWorkshifts(this.workshiftMapper.toEntities(riderDto.getWorkshifts()));
        }

        return riderEntity;
    }

    public RiderDto toDto(RiderEntity riderEntity) {
        if (riderEntity == null) {
            return null;
        }

        RiderDto riderDto = new RiderDto();

        riderDto.setId(riderEntity.getId());
        riderDto.setEmail(riderEntity.getEmail());
        riderDto.setPassword(riderEntity.getPassword());
        riderDto.setIsDeleted(riderEntity.getIsDeleted());
        riderDto.setIsVerified(riderEntity.getIsVerified());
        riderDto.setUserDetails(this.userDetailsMapper.toDto(riderEntity.getUserDetails()));

        riderDto.setStartingPosition(riderEntity.getStartingPosition());
        riderDto.setCurrentPosition(riderEntity.getCurrentPosition());
        riderDto.setAvailable(riderEntity.getAvailable());

        if (riderEntity.getWorkshifts() != null) {
            riderDto.setWorkshifts(this.workshiftMapper.toDtos(riderEntity.getWorkshifts()));
        }

        return riderDto;
    }

}