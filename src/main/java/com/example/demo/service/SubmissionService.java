package com.example.demo.service;

import com.example.demo.model.dto.SubmissionDTO;

public interface SubmissionService {

    SubmissionDTO createSubmission(SubmissionDTO submissionDTO);

    SubmissionDTO gradeSubmission(Integer submissionId, Float grade);

}
