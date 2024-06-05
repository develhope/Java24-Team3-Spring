package com.develhope.spring.mappers;

import com.develhope.spring.dto.AdminDTO;
import com.develhope.spring.entities.AdminEntity;
import org.springframework.stereotype.Component;


@Component
public class AdminMapper {

    public AdminEntity toEntity(AdminDTO adminDTO) {
        if (adminDTO == null) {
            return null;
        }

        AdminEntity adminEntity = new AdminEntity();

        adminEntity.setEmail(adminDTO.getEmail());
        adminEntity.setPassword(adminDTO.getPassword());
        adminEntity.setName(adminDTO.getName());
        adminEntity.setSurname(adminDTO.getSurname());
        adminEntity.setPhoneNumber(adminDTO.getPhoneNumber());
        adminEntity.setIsDeleted(adminDTO.getIsDeleted());
        adminEntity.setIsVerified(adminDTO.getIsVerified());

        return adminEntity;
    }

    public AdminDTO toDTO(AdminEntity adminEntity) {
        if (adminEntity == null) {
            return null;
        }

        AdminDTO adminDTO = new AdminDTO();

        adminDTO.setEmail(adminEntity.getEmail());
        adminDTO.setPassword(adminEntity.getPassword());
        adminDTO.setName(adminEntity.getName());
        adminDTO.setSurname(adminEntity.getSurname());
        adminDTO.setPhoneNumber(adminEntity.getPhoneNumber());
        adminDTO.setIsDeleted(adminEntity.getIsDeleted());
        adminDTO.setIsVerified(adminEntity.getIsVerified());

        return adminDTO;
    }
}
