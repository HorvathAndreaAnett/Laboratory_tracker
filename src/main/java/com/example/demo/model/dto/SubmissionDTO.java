package com.example.demo.model.dto;

import com.example.demo.model.Submission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SubmissionDTO {

    private Integer id;

    private Integer userId;

    private Integer assignmentId;

    private String link;
    private String comment;
    private Float grade;

    public static SubmissionDTO fromEntity(Submission submission) {
        return SubmissionDTO.builder()
                .id(submission.getId())
                .userId(submission.getUser().getId())
                .assignmentId(submission.getAssignment().getId())
                .link(submission.getLink())
                .comment(submission.getComment())
                .grade(submission.getGrade())
                .build();
    }
}
