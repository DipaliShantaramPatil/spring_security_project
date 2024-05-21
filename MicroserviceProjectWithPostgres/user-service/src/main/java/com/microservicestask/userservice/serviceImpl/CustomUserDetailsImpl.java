package com.microservicestask.userservice.serviceImpl;

import com.microservicestask.userservice.dto.CustomUserDetails;
import com.microservicestask.userservice.entity.User;
import com.microservicestask.userservice.repository.UserServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserServiceRepo userServiceRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user from the database
        User user=userServiceRepo.findByEmailId(username).orElseThrow(() -> new RuntimeException("User not found"));
        if(user == null)
        {
            throw new UsernameNotFoundException("Could not found");
        }
        CustomUserDetails customUserDetails=new CustomUserDetails(user);
        return customUserDetails;
    }
}
