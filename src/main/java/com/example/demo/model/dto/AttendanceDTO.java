package com.example.demo.model.dto;


import com.example.demo.model.Attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class AttendanceDTO {

    private Integer id;

    private Integer userId;

    private Integer laboratoryId;

    public static AttendanceDTO fromEntity(Attendance attendance) {
        return AttendanceDTO.builder()
                .id(attendance.getId())
                .userId(attendance.getUser().getId())
                .laboratoryId(attendance.getLaboratory().getId())
                .build();
    }


}
