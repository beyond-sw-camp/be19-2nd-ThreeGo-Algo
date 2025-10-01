package com.threego.algomemberservice.auth.command.application.service;

import com.threego.algomemberservice.auth.command.application.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    void registUser(UserDTO newUser);
}
