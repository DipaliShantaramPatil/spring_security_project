package com.microservicestask.userservice.config;

import com.microservicestask.userservice.security.JwtHelper;
import com.microservicestask.userservice.util.Constant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class PatientFeignClientConfiguration implements RequestInterceptor {

    private final JwtHelper jwtHelper;

    public PatientFeignClientConfiguration(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = getToken();
        if (token != null) {
            System.out.println("Generated Token is " + token);
            requestTemplate.header(Constant.AUTHORIZATION, Constant.BEARER + token);
        } else {
            throw new RuntimeException("No authentication token");
        }
    }

    private String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtHelper.generateToken(userDetails);
        }
        return null;
    }

}
