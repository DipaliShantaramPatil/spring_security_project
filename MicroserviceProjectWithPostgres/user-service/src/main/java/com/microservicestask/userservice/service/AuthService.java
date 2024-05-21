package com.microservicestask.userservice.service;

import com.microservicestask.userservice.dto.JwtResponse;

public interface AuthService {
    JwtResponse login(String email, String password);

}
