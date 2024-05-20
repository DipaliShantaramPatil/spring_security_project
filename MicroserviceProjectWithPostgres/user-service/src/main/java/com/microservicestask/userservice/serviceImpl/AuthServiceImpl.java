package com.microservicestask.userservice.serviceImpl;

import com.microservicestask.userservice.dto.JwtResponse;
import com.microservicestask.userservice.security.JwtHelper;
import com.microservicestask.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public JwtResponse login(String email, String password) {
        doAuthenticate(email, password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String token = jwtHelper.generateToken(userDetails);
        return new JwtResponse(token, userDetails.getUsername());
    }

    private void doAuthenticate(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
