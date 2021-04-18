package com.example.demo.model.dto;

import com.example.demo.model.Laboratory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class LaboratoryDTO {

    private Integer id;
    private Integer nr;
    private Date date;
    private String title;
    private String curricula;
    private String description;
    private Integer assignmentId;
    private Set<AttendanceDTO> attendanceSet;

    public static LaboratoryDTO fromEntity(Laboratory laboratory) {
        return LaboratoryDTO.builder()
                .id(laboratory.getId())
                .nr(laboratory.getNr())
                .date(laboratory.getDate())
                .title(laboratory.getTitle())
                .curricula(laboratory.getCurricula())
                .description(laboratory.getDescription())
                .build();
    }
}
