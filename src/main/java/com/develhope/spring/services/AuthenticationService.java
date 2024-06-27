package com.develhope.spring.services;


import com.develhope.spring.daos.AdminDao;
import com.develhope.spring.daos.CustomerDao;
import com.develhope.spring.daos.OwnerDao;
import com.develhope.spring.daos.RiderDao;
import com.develhope.spring.models.costants.Role;
import com.develhope.spring.models.dtos.LoginUserDto;
import com.develhope.spring.models.dtos.RegisterUserDto;
import com.develhope.spring.models.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RiderDao riderDao;
    @Autowired
    private OwnerDao ownerDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

//    public UserEntity signup(RegisterUserDto input) {
//        return userRepository.save(new UserEntity(input.getEmail(), passwordEncoder.encode(input.getPassword()), input.getRole()));
//    }


    public UserEntity signup(RegisterUserDto registerUserDto, Role role) {
        return switch (role){
            case Role.ADMIN -> adminDao.save(new AdminEntity(registerUserDto.getEmail(), passwordEncoder.encode(registerUserDto.getPassword())));
            case Role.CUSTOMER -> customerDao.save(new CustomerEntity(registerUserDto.getEmail(), passwordEncoder.encode(registerUserDto.getPassword())));
            case Role.OWNER -> ownerDao.save(new OwnerEntity(registerUserDto.getEmail(), passwordEncoder.encode(registerUserDto.getPassword())));
            case Role.RIDER -> riderDao.save(new RiderEntity(registerUserDto.getEmail(), passwordEncoder.encode(registerUserDto.getPassword())));
        };
    }

    public UserEntity authenticate(LoginUserDto loginUserDto, Role role) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginUserDto.getEmail(),
                loginUserDto.getPassword()
            )
        );

        return (UserEntity) switch (role){
            case Role.ADMIN -> adminDao.findAdminByEmail(loginUserDto.getEmail()).get();
            case Role.CUSTOMER -> customerDao.findCustomerByEmail(loginUserDto.getEmail()).get();
            case Role.OWNER -> ownerDao.findOwnerByEmail(loginUserDto.getEmail()).get();
            case Role.RIDER -> riderDao.findByEmail(loginUserDto.getEmail()).get();
        };

//        Optional<UserEntity> userEntity = switch (role){
//            case Role.ADMIN -> null;
//            case Role.CUSTOMER -> customerRepository.findByEmail(loginUserDto.getEmail());
//            case Role.OWNER -> null;
//            case Role.DRIVER -> null;
//        };

    }


}
