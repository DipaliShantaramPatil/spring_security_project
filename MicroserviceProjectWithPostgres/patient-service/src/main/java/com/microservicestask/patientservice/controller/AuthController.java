package com.microservicestask.patientservice.controller;


import com.microservicestask.patientservice.dto.JwtRequest;
import com.microservicestask.patientservice.dto.JwtResponse;
import com.microservicestask.patientservice.entity.Patient;
import com.microservicestask.patientservice.security.JwtHelper;
import com.microservicestask.patientservice.service.AuthService;
import com.microservicestask.patientservice.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        JwtResponse response = authService.login(request.getEmailId(), request.getPassword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create-patient")
    public Patient createUser(@RequestBody Patient patient)
    {
        return patientService.createPatient(patient);
    }
}

