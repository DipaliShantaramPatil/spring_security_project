package com.microservicestask.patientservice.repository;

import com.microservicestask.patientservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientServiceRepo extends JpaRepository<Patient,Integer> {
    public Optional<Patient> findByEmailId(String emailId);

}
