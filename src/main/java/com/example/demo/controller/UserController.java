package com.example.demo.controller;


import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/teacher/retrieveUsers")
    public ResponseEntity<?> retrieveUsers() {
        List<UserDTO> userDTOList = userService.retrieveAllUsers();
        if (userDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/teacher/retrieveStudents")
    public ResponseEntity<?> retrieveStudents() {
        Set<UserDTO> userDTOSet = userService.retrieveAllStudents();
        if (userDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOSet, HttpStatus.OK);
    }

    @GetMapping("/teacher/retrieveUserByName")
    public ResponseEntity<?> retrieveUserByName(@RequestBody String name) {
        Set<UserDTO> userDTOSet = userService.retrieveUserByName(name);
        if (userDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOSet, HttpStatus.OK);
    }

    @GetMapping("/teacher/retrieveUserByEmail")
    public ResponseEntity<?> retrieveUserByEmail(@RequestBody String email) {
        Set<UserDTO> userDTOSet = userService.retrieveUserByEmailAddr(email);
        if (userDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOSet, HttpStatus.OK);
    }

    @DeleteMapping("/teacher/deleteUserById")
    public ResponseEntity<?> deleteUserById(@RequestBody Integer id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/teacher/createStudent")
    public ResponseEntity<?> createStudent(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createStudent(userDTO);
        if (createdUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerStudent(@RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.registerStudent(userDTO);
        if (registeredUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }
}
