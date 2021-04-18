package com.example.demo.controller;

import com.example.demo.model.dto.SubmissionDTO;
import com.example.demo.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping("/student/createSubmission")
    public ResponseEntity<?> createSubmission(@RequestBody SubmissionDTO submissionDTO) {
        SubmissionDTO createdSubmission = submissionService.createSubmission(submissionDTO);
        if (createdSubmission == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdSubmission, HttpStatus.OK);
    }

    @PostMapping("/teacher/gradeSubmission")
    public ResponseEntity<?> gradeSubmission(@RequestParam(value = "submissionId") Integer submissionId, @RequestParam(value = "grade") Float grade) {
        SubmissionDTO submissionDTO = submissionService.gradeSubmission(submissionId, grade);
        if (submissionDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(submissionDTO, HttpStatus.OK);
    }
}
