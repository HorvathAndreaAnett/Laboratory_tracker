package com.example.demo.service;

import com.example.demo.model.Laboratory;
import com.example.demo.model.dto.LaboratoryDTO;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.repository.LaboratoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.model.Constants.laboratoryNr_MAX;
import static com.example.demo.model.Constants.laboratoryNr_MIN;

@Service
public class LaboratoryServiceImplementation implements LaboratoryService {

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LaboratoryDTO> retrieveAllLaboratories() {
        return laboratoryRepository.findAll().stream().map(LaboratoryDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteLaboratory(Integer id) {
        laboratoryRepository.deleteById(id);
    }

    @Override
    public LaboratoryDTO createLaboratory(LaboratoryDTO laboratoryDTO) {
        if (laboratoryRepository.findAll().stream()   //if the laboratory number already exists
                .filter(l -> l.getNr().equals(laboratoryDTO.getNr()))
                .collect(Collectors.toList()).size() != 0 || !isLaboratoryNrValid(laboratoryDTO.getNr())) {
            return null;
        }
        Laboratory laboratory = modelMapper.map(laboratoryDTO, Laboratory.class);
        if (laboratoryDTO.getAssignmentId() != null) {
            laboratory.setAssignment(assignmentRepository.findById(laboratoryDTO.getAssignmentId()).orElse(null));
        }
        laboratory = laboratoryRepository.save(laboratory);
        return LaboratoryDTO.fromEntity(laboratory);
    }

    @Override
    public LaboratoryDTO editLaboratory(LaboratoryDTO laboratoryDTO) {
        if (laboratoryRepository.findAll().stream()   //if the laboratory number already exists
                .filter(l -> l.getNr().equals(laboratoryDTO.getNr()))
                .collect(Collectors.toList()).size() != 0 || !isLaboratoryNrValid(laboratoryDTO.getNr())) {
            return null;
        }
        Laboratory laboratory = laboratoryRepository.findById(laboratoryDTO.getId()).orElse(null);
        if (laboratory == null) {
            return null;
        }
        laboratory = modelMapper.map(laboratoryDTO, Laboratory.class);
        if (laboratoryDTO.getAssignmentId() != null) {
            laboratory.setAssignment(assignmentRepository.findById(laboratoryDTO.getAssignmentId()).orElse(null));
        }        laboratory = laboratoryRepository.save(laboratory);
        return LaboratoryDTO.fromEntity(laboratory);
    }

    private boolean isLaboratoryNrValid(Integer laboratoryNr) {
        if (laboratoryNr >= laboratoryNr_MIN && laboratoryNr <= laboratoryNr_MAX) {
            return true;
        }
        return false;
    }

}
