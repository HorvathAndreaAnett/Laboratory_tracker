package com.example.demo.controller;

import com.example.demo.model.dto.AssignmentDTO;
import com.example.demo.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/teacher/retrieveAllAssignments")
    private ResponseEntity<?> retrieveAllAssignments() {
        List<AssignmentDTO> assignmentDTOList = assignmentService.retrieveAllAssignments();
        if (assignmentDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(assignmentDTOList, HttpStatus.OK);
    }

    @GetMapping({"/teacher/retrieveAssignmentsForLaboratory", "/student/retrieveAssignmentsForLaboratory"})
    private ResponseEntity<?> retrieveAssignmentsForLaboratory(@RequestBody Integer laboratoryId) {
        List<AssignmentDTO> assignmentDTOList = assignmentService.retrieveAssignmentForLaboratory(laboratoryId);
        if (assignmentDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(assignmentDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/teacher/deleteAssignmentById")
    private ResponseEntity<?> deleteAssignmentById(@RequestBody Integer id) {
        try {
            assignmentService.deleteAssignment(id);
            return new ResponseEntity<>("Assignment deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Assignment not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/teacher/createAssignment")
    private ResponseEntity<?> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        AssignmentDTO createdAssignment = assignmentService.createAssignment(assignmentDTO);
        if (createdAssignment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdAssignment, HttpStatus.OK);
    }

    @PostMapping("/teacher/editAssignment")
    private ResponseEntity<?> editAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        AssignmentDTO editedAssignment = assignmentService.editAssignment(assignmentDTO);
        if (editedAssignment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(editedAssignment, HttpStatus.OK);
    }
}
