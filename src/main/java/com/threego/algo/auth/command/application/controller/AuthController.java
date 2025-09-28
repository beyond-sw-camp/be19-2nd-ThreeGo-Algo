package com.threego.algo.auth.command.application.controller;

import com.threego.algo.auth.command.application.dto.RequestRegistUserDTO;
import com.threego.algo.auth.command.application.dto.ResponseRegistUserDTO;
import com.threego.algo.auth.command.application.dto.UserDTO;
import com.threego.algo.auth.command.application.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class AuthController {

    private AuthService authService;
    private final ModelMapper modelMapper;

    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;

    }

    @PostMapping("/auth/signup")
    public ResponseEntity<ResponseRegistUserDTO> registUser(@RequestBody RequestRegistUserDTO newUser) {
        UserDTO userDTO = modelMapper.map(newUser, UserDTO.class);

        authService.registUser(userDTO);

        ResponseRegistUserDTO responseUser = modelMapper.map(userDTO, ResponseRegistUserDTO.class);
        log.info(responseUser.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
