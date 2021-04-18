package com.example.demo.model.dto;

import com.example.demo.model.Assignment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class AssignmentDTO {

    private Integer id;

    private String name;
    private Date deadline;
    private String description;
    private Integer laboratoryId;

    private Set<SubmissionDTO> submissionSet;

    public static AssignmentDTO fromEntity(Assignment assignment) {
        return AssignmentDTO.builder()
                .id(assignment.getId())
                .name(assignment.getName())
                .deadline(assignment.getDeadline())
                .description(assignment.getDescription())
                .laboratoryId(assignment.getLaboratory().getId())
                .build();
    }
}
