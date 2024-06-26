package com.develhope.spring.controllers;


import com.develhope.spring.mappers.RoleMapper;
import com.develhope.spring.models.LoginResponse;
import com.develhope.spring.models.costants.Role;
import com.develhope.spring.models.dtos.LoginUserDto;
import com.develhope.spring.models.dtos.RegisterUserDto;
import com.develhope.spring.models.entities.UserEntity;
import com.develhope.spring.services.AuthenticationService;
import com.develhope.spring.services.JwtService;
import com.develhope.spring.validators.IdValidator;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    IdValidator idValidator;

    @PostMapping("/signup/{role}")
    public ResponseEntity<RegisterUserDto> register(@PathVariable("role") String roleString, @RequestBody RegisterUserDto registerUserDto) throws Exception {
        Role role = roleMapper.toRole(roleString);
        idValidator.noId(registerUserDto.getId());
        UserEntity registeredUser = authenticationService.signup(registerUserDto, role);

        return ResponseEntity.ok(new RegisterUserDto(registeredUser.getId(),registeredUser.getEmail(),registeredUser.getPassword()));
    }

//    @PostMapping("/signup/{role}/{id}")
//    public ResponseEntity<UserEntity> completeProfile(@PathVariable("role") String roleString, @PathParam(name)) throws Exception {
//        Role role = roleMapper.toRole(roleString);
//        UserEntity registeredUser = authenticationService.signup(registerUserDto, role);
//
//        return ResponseEntity.ok(registeredUser);
//    }

    @PostMapping("/login/{role}")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto, @PathVariable(value = "role", required = true) String roleString) throws Exception {

        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto, roleMapper.toRole(roleString));

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}