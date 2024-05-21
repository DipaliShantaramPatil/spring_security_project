package com.microservicestask.patientservice.controller;

import com.microservicestask.patientservice.entity.Patient;
import com.microservicestask.patientservice.exception.ErrorResponse;
import com.microservicestask.patientservice.exception.SuccessResponse;
import com.microservicestask.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientServiceController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
        try {
            Patient createdPatient = patientService.createPatient(patient);
            SuccessResponse response = new SuccessResponse(LocalDateTime.now(), HttpStatus.CREATED.value(), "Patient created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllPatient() {
        try {
            List<Patient> patients = patientService.getAllPatient();
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable int id) {
        try {
            Optional<Patient> patient = patientService.getOnePatient(id);
            if (patient.isPresent()) {
                return ResponseEntity.ok(patient.get());
            } else {
                ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Patient not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable int id, @RequestBody Patient patient
    ) {
        try {
            Patient updatedPatient = patientService.updatePatient(id,patient);
            if (updatedPatient != null) {
                SuccessResponse response = new SuccessResponse(LocalDateTime.now(), HttpStatus.OK.value(), "User updated successfully");
                return ResponseEntity.ok(response);
            } else {
                ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatientById(@PathVariable int id) {
        try {
            patientService.deletePatient(id);
            SuccessResponse response = new SuccessResponse(LocalDateTime.now(), HttpStatus.OK.value(), "User deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
