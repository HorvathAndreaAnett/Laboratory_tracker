package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repository.GrupaRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.model.Constants.studentRoleId;
import static com.example.demo.model.Constants.tokenLength;

import org.apache.commons.lang3.RandomStringUtils;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GrupaRepository grupaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<UserDTO> retrieveAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Set<UserDTO> retrieveAllStudents() {
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getRole().getId().equals(studentRoleId))
                .map(UserDTO::fromEntityToStudent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserDTO> retrieveUserByName(String name) {
        return  userRepository.findAll()
                .stream()
                .filter(u -> u.getFullName().equals(name))
                .map(UserDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserDTO> retrieveUserByEmailAddr(String emailAddr) {
        return userRepository.findUserByEmail(emailAddr).stream().map(UserDTO::fromEntity).collect(Collectors.toSet());
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO createStudent(UserDTO userDTO) {

        String generatedToken = RandomStringUtils.randomAlphanumeric(tokenLength);

        User user = modelMapper.map(userDTO, User.class);
        user.setRole(roleRepository.findById(studentRoleId).orElse(null));
        user.setGrupa(grupaRepository.findById(userDTO.getGrupaId()).orElse(null));
        user.setToken(generatedToken);
        user = userRepository.save(user);
        return UserDTO.fromEntityToStudent(user);
    }

    @Override
    public UserDTO registerStudent(UserDTO userDTO) {
        if (!isUserDataValid(userDTO)) {
            return null;
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = userRepository.findUserByEmail(userDTO.getEmailAddr()).get(0);
        if (user.getToken().equals(userDTO.getToken())) {
            user.setUsername(userDTO.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            user.setHobby(userDTO.getHobby());
            user =  userRepository.save(user);
            return UserDTO.fromEntityToStudent(user);
        }
        return null;
    }

    private boolean isUserDataValid(UserDTO userDTO) {
        if (userDTO.getEmailAddr().contains("@")
                && userDTO.getPassword().length() > 5 && userDTO.getPassword().matches("[a-zA-Z1-9]+")
                && userDTO.getFirstName().matches("[a-zA-Z]+")
                && userDTO.getLastName().matches("[a-zA-Z]+")) {
            return true;
        }
        return false;
    }

}
