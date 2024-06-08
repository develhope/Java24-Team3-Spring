package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.AdminDto;
import com.develhope.spring.models.entities.AdminEntity;
import org.springframework.stereotype.Component;


@Component
public class AdminMapper {

    public AdminEntity toEntity(AdminDto adminDTO) {
        if (adminDTO == null) {
            return null;
        }

        AdminEntity adminEntity = new AdminEntity();

        adminEntity.setId(adminDTO.getId());
        adminEntity.setEmail(adminDTO.getEmail());
        adminEntity.setPassword(adminDTO.getPassword());
        adminEntity.setName(adminDTO.getName());
        adminEntity.setSurname(adminDTO.getSurname());
        adminEntity.setPhoneNumber(adminDTO.getPhoneNumber());
        adminEntity.setIsDeleted(adminDTO.getIsDeleted());
        adminEntity.setIsVerified(adminDTO.getIsVerified());

        return adminEntity;
    }

    public AdminDto toDto(AdminEntity adminEntity) {
        if (adminEntity == null) {
            return null;
        }

        AdminDto adminDto = new AdminDto();

        adminDto.setId(adminEntity.getId());
        adminDto.setEmail(adminEntity.getEmail());
        adminDto.setPassword(adminEntity.getPassword());
        adminDto.setsetName(adminEntity.getName());
        adminDto.setSurname(adminEntity.getSurname());
        adminDto.setPhoneNumber(adminEntity.getPhoneNumber());
        adminDto.setIsDeleted(adminEntity.getIsDeleted());
        adminDto.setIsVerified(adminEntity.getIsVerified());

        return adminDto;
    }
}
