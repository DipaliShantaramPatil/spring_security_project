package com.microservicestask.patientservice.service;

import com.microservicestask.patientservice.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    public Patient createPatient(Patient patient);

    public Optional<Patient> getOnePatient(int id);

    public List<Patient> getAllPatient();

    public Patient updatePatient(int id, Patient updatedPatient);

    public void deletePatient(int id);
}
