package com.microservicestask.userservice.controller;

import com.microservicestask.userservice.entity.Patient;
import com.microservicestask.userservice.entity.User;
import com.microservicestask.userservice.exception.ErrorResponse;
import com.microservicestask.userservice.exception.SuccessResponse;
import com.microservicestask.userservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/createpatient")
    public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
        try {
            Patient createdPatient = patientService.createPatient(patient);
            SuccessResponse response=new SuccessResponse(LocalDateTime.now(), HttpStatus.CREATED.value(),"Patient created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getpatients")
    public ResponseEntity<?> getAllPatients() {
        try {
            List<Patient> patients = patientService.getAllPatient();
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getpatient/{id}")
    public ResponseEntity<?> getOnePatient(@PathVariable int id) {
        try {
            Patient patient = patientService.getOnePatient(id);
            if (patient != null) {
                return ResponseEntity.ok(patient);
            } else {
                ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Patient not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
