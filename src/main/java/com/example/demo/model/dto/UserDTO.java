package com.example.demo.model.dto;

import com.example.demo.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private Integer roleId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String token;
    private String emailAddr;

    private Integer grupaId;

    private String hobby;

    private Set<AttendanceDTO> attendenceSet;

    private Set<SubmissionDTO> submissionSet;


    public static UserDTO fromEntityToStudent(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .roleId(user.getRole().getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .token(user.getToken())
                .emailAddr(user.getEmailAddr())
                .grupaId(user.getGrupa().getId())
                .build();
    }

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .roleId(user.getRole().getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .emailAddr(user.getEmailAddr())
                .build();
    }
}
