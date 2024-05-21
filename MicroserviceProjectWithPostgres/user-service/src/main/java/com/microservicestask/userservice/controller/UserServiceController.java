package com.microservicestask.userservice.controller;
import com.microservicestask.userservice.entity.User;
import com.microservicestask.userservice.exception.ErrorResponse;
import com.microservicestask.userservice.exception.SuccessResponse;
import com.microservicestask.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Tag(name = "UserServiceController", description = "to perform operations on UserService")
public class UserServiceController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Post operations on user",
            description = "It is used to save user object in database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success | Ok"),
            @ApiResponse(responseCode = "401", description = "not authorized !!"),
            @ApiResponse(responseCode = "201", description = "new user created !!")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            SuccessResponse response = new SuccessResponse(LocalDateTime.now(), HttpStatus.CREATED.value(), "User created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(
            summary = "Get operations on user",
            description = "It is used to retrieve user object in database"
    )
    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUser();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(
            summary = "Get operations on user by using userId",
            description = "It is used to retrieve user object in database using userId"
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            Optional<User> user = Optional.ofNullable(userService.getOneUser(id));
            if (user != null) {
                return ResponseEntity.ok(user);
            } else  {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "User not found");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(
            summary = "Put operations on user by using userId",
            description = "It is used to update user object in database using userId"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user
    ) {
        try {
            User updatedUser = userService.updateUser(id,user);
            if (updatedUser != null) {
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

    @Operation(
            summary = "Delete operations on user by using userId",
            description = "It is used to delete user object in database using userId"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            SuccessResponse response = new SuccessResponse(LocalDateTime.now(), HttpStatus.OK.value(), "User deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @Operation(
            summary = "Get operations on user",
            description = "It is used to retrieve currentuser object "
    )
    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal)
    {
        return principal.getName();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/userrole/info")
    public List<User> getUserInfo() {
        return userService.getUserInfo();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/adminrole/info")
    public List<User> getAdminInfo() {
        return userService.getUserInfo();
    }

}
