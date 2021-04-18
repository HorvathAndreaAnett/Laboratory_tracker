package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<UserDTO> retrieveAllUsers();

    Set<UserDTO> retrieveAllStudents();

    Set<UserDTO> retrieveUserByName(String name);

    Set<UserDTO> retrieveUserByEmailAddr(String emailAddr);

    void deleteUserById(Integer id);

    UserDTO createStudent(UserDTO userDTO);

    UserDTO registerStudent(UserDTO userDTO);
}
