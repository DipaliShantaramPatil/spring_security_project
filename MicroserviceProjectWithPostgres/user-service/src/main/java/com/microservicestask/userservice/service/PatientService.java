package com.microservicestask.userservice.service;

import com.microservicestask.userservice.config.PatientFeignClientConfiguration;
import com.microservicestask.userservice.entity.Patient;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@FeignClient(name = "PATIENT-SERVICE",configuration = PatientFeignClientConfiguration.class)
public interface PatientService {

    @PostMapping("/patient/create")
    @Headers("Content-Type: application/json")
    Patient createPatient(@RequestBody Patient Patient);

    @GetMapping("/patient/get")
    @Headers("Content-Type: application/json")
    List<Patient> getAllPatient();

    @GetMapping("/patient/get/{id}")
    @Headers("Content-Type: application/json")
    Patient getOnePatient(@PathVariable int id);

}
