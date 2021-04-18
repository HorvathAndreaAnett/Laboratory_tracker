package com.example.demo.service;

import com.example.demo.model.dto.LaboratoryDTO;

import java.util.List;


public interface LaboratoryService {

    void deleteLaboratory(Integer id);

    LaboratoryDTO createLaboratory(LaboratoryDTO laboratoryDTO);

    LaboratoryDTO editLaboratory(LaboratoryDTO laboratoryDTO);

    List<LaboratoryDTO> retrieveAllLaboratories();
}
