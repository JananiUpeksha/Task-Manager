package com.example.taskmanager.controller;

import com.example.taskmanager.dto.UserDTO;
import com.example.taskmanager.secure.JWTAuthResponse;
import com.example.taskmanager.secure.SignIn;
import com.example.taskmanager.service.AuthService;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private AuthService authService;  // Use AuthService for handling sign-in and sign-up
    @Autowired
    private UserService userService;

    // SignUp: Register a new user and return the JWT token
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {
        try {
            JWTAuthResponse jwtAuthResponse = authService.signUp(userDTO);  // Call signUp method
            return new ResponseEntity<>(jwtAuthResponse, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // SignIn: Authenticate the user and return the JWT token
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignIn signIn) {
        try {
            JWTAuthResponse jwtAuthResponse = authService.signIn(signIn);  // Call signIn method
            return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);  // Unauthorized error for invalid credentials
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserDTO> users = userService.findAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching users", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
