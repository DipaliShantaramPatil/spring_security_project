package com.microservicestask.patientservice.serviceImpl;


import com.microservicestask.patientservice.entity.Patient;
import com.microservicestask.patientservice.repository.PatientServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsImpl implements UserDetailsService {

    @Autowired
    private PatientServiceRepo patientServiceRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient=patientServiceRepo.findByEmailId(username).orElseThrow(() -> new RuntimeException("Patient not found"));
        return patient;
    }
}
