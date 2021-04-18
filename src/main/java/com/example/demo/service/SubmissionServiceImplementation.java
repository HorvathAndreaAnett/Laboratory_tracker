package com.example.demo.service;

import com.example.demo.model.Submission;
import com.example.demo.model.dto.SubmissionDTO;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.repository.SubmissionRepository;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.example.demo.model.Constants.grade_MAX;
import static com.example.demo.model.Constants.grade_MIN;

@Service
public class SubmissionServiceImplementation implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SubmissionDTO createSubmission(SubmissionDTO submissionDTO) {
        if (submissionRepository.findAll().stream() //a submission made by user U for assignment A already exists
                .filter(s -> s.getAssignment().getId().equals(submissionDTO.getAssignmentId()) &&
                        s.getUser().getId().equals(submissionDTO.getUserId()))
                .collect(Collectors.toList()).size() != 0) {
            return null;
        }
        Submission submission = modelMapper.map(submissionDTO, Submission.class);
        submission.setGrade((float) 0);
        submission.setUser(userRepository.findById(submissionDTO.getUserId()).orElse(null));
        submission.setAssignment(assignmentRepository.findById(submissionDTO.getAssignmentId()).orElse(null));
        submission = submissionRepository.save(submission);
        return SubmissionDTO.fromEntity(submission);
    }

    @Override
    public SubmissionDTO gradeSubmission(Integer submissionId, Float grade) {
        if (submissionRepository.findById(submissionId) == null || !isGradeValid(grade)) {
            return null;
        }
        Submission submission = submissionRepository.findById(submissionId).orElse(null);
        submission.setGrade(grade);
        submission = submissionRepository.save(submission);
        return SubmissionDTO.fromEntity(submission);
    }

    private boolean isGradeValid(Float grade) {
        if (grade >= grade_MIN && grade <= grade_MAX) {
            return true;
        }
        return false;
    }


}
