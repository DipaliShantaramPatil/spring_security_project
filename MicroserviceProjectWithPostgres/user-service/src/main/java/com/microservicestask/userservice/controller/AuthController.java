package com.microservicestask.userservice.controller;

import com.microservicestask.userservice.dto.JwtRequest;
import com.microservicestask.userservice.dto.JwtResponse;
import com.microservicestask.userservice.entity.User;
import com.microservicestask.userservice.service.AuthService;
import com.microservicestask.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "AuthController", description = "It Perform the Authentication operations")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Operation(
            summary = "Post operations",
            description = "It is used to save object in database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success | Ok"),
            @ApiResponse(responseCode = "401", description = "not authorized !!"),
            @ApiResponse(responseCode = "201", description = "new user created !!")
    })
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        JwtResponse response = authService.login(request.getEmailId(), request.getPassword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(
            summary = "Post operations on user",
            description = "It is used to save user object in database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success | Ok"),
            @ApiResponse(responseCode = "401", description = "not authorized !!"),
            @ApiResponse(responseCode = "201", description = "new user created !!")
    })
    @PostMapping("/create-user")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

}

