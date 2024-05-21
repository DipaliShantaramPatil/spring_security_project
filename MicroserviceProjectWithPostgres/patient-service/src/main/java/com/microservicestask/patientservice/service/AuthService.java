package com.microservicestask.patientservice.service;

import com.microservicestask.patientservice.dto.JwtResponse;

public interface AuthService {

    JwtResponse login(String email, String password);

}
