package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.UserDetailsDto;
import com.develhope.spring.models.entities.UserDetailsEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetailsEntity toEntity(UserDetailsDto userDetailsDto) {
        if (userDetailsDto == null) {
            return null;
        }

        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();

        userDetailsEntity.setId(userDetailsDto.getId());
        userDetailsEntity.setName(userDetailsDto.getName());
        userDetailsEntity.setSurname(userDetailsDto.getSurname());
        userDetailsEntity.setBirthDate(userDetailsDto.getBirthDate());
        userDetailsEntity.setPhoneNumber(userDetailsDto.getPhoneNumber());
        userDetailsEntity.setCreationDate(userDetailsDto.getCreationDate());
        userDetailsEntity.setUpdateDate(userDetailsDto.getUpdateDate());

        return userDetailsEntity;
    }

    public UserDetailsDto toDto(UserDetailsEntity userDetailsEntity) {
        if (userDetailsEntity == null) {
            return null;
        }

        UserDetailsDto userDetailsDto = new UserDetailsDto();

        userDetailsDto.setId(userDetailsEntity.getId());
        userDetailsDto.setName(userDetailsEntity.getName());
        userDetailsDto.setSurname(userDetailsEntity.getSurname());
        userDetailsDto.setBirthDate(userDetailsEntity.getBirthDate());
        userDetailsDto.setPhoneNumber(userDetailsEntity.getPhoneNumber());
        userDetailsDto.setCreationDate(userDetailsEntity.getCreationDate());
        userDetailsDto.setUpdateDate(userDetailsEntity.getUpdateDate());

        return userDetailsDto;
    }

}
