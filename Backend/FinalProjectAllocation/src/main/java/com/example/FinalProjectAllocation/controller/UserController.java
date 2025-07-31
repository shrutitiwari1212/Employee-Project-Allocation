package com.example.FinalProjectAllocation.controller;

import com.example.FinalProjectAllocation.dto.LoginDTO;
import com.example.FinalProjectAllocation.dto.ResponseDTO;
import com.example.FinalProjectAllocation.dto.SignUpDTO;

import com.example.FinalProjectAllocation.dto.UpdatePasswordRequest;
import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.UserEntity;
import com.example.FinalProjectAllocation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDTO signupDTO) {
        userService.registerUser(signupDTO);
        return ResponseEntity.ok("User registered successfully!");
    }

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO request) {
        boolean isAuthenticated = userService.authenticate(request.getUsername(), request.getPassword());
        if (isAuthenticated){
            UserEntity user =userService.findByUsername(request.getUsername());
            EmployeeEntity employee = user.getEmployeeEntity();
            return ResponseEntity.ok(employee);
            //return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");

        }
    }*/

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        try{
            ResponseDTO response = userService.login(loginDTO);
            return ResponseEntity.ok(response);
        } catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO(e.getMessage(), null, null, null));
        }
    }


    @PutMapping("/updatePassword")
    public ResponseEntity<ResponseDTO> updatePassword(@RequestBody UpdatePasswordRequest request){
        String result = userService.updatePassword(request);
        ResponseDTO response = new ResponseDTO();
        response.setMessage(result);
        if("Password updated successfully!".equals(result)) {
            return ResponseEntity.ok(response);
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }





}
