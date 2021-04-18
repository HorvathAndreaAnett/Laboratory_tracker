package com.example.demo.service;

import com.example.demo.model.Assignment;
import com.example.demo.model.dto.AssignmentDTO;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.repository.LaboratoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImplementation implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void deleteAssignment(Integer id) {
        assignmentRepository.deleteById(id);
    }

    @Override
    public AssignmentDTO createAssignment(AssignmentDTO assignmentDTO) {
        if (assignmentRepository.findAll().stream() //an assignment has already been submitted for the laboratory
                .filter(a -> a.getLaboratory().getId().equals(assignmentDTO.getLaboratoryId()))
                .collect(Collectors.toList()).size() != 0) {
            return null;
        }
        Assignment assignment = modelMapper.map(assignmentDTO, Assignment.class);
        assignment.setLaboratory(laboratoryRepository.findById(assignmentDTO.getLaboratoryId()).orElse(null));
        assignment = assignmentRepository.save(assignment);
        return AssignmentDTO.fromEntity(assignment);
    }

    @Override
    public AssignmentDTO editAssignment(AssignmentDTO assignmentDTO) {
        if (assignmentRepository.findById(assignmentDTO.getId()) == null) {
            return null;
        }
        Assignment assignment = modelMapper.map(assignmentDTO, Assignment.class);
        assignment.setLaboratory(laboratoryRepository.findById(assignmentDTO.getLaboratoryId()).orElse(null));
        assignment = assignmentRepository.save(assignment);
        return AssignmentDTO.fromEntity(assignment);
    }

    @Override
    public List<AssignmentDTO> retrieveAllAssignments() {
        return assignmentRepository.findAll().stream().map(AssignmentDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<AssignmentDTO> retrieveAssignmentForLaboratory(Integer laboratoryId) {
        return assignmentRepository.findAll().stream()
                .filter(a -> a.getLaboratory().getId().equals(laboratoryId))
                .map(AssignmentDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
