package com.example.demo.service;

import com.example.demo.model.dto.AssignmentDTO;

import java.util.List;

public interface AssignmentService {

    void deleteAssignment(Integer id);

    AssignmentDTO createAssignment(AssignmentDTO assignmentDTO);

    AssignmentDTO editAssignment(AssignmentDTO assignmentDTO);

    List<AssignmentDTO> retrieveAllAssignments();

    List<AssignmentDTO> retrieveAssignmentForLaboratory(Integer laboratoryId);
}
