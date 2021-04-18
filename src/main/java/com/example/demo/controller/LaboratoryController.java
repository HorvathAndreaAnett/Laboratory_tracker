package com.example.demo.controller;

import com.example.demo.model.dto.LaboratoryDTO;
import com.example.demo.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @DeleteMapping("/teacher/deleteLaboratoryById")
    public ResponseEntity<?> deleteLaboratoryById(@RequestBody Integer id) {
        try {
            laboratoryService.deleteLaboratory(id);
            return new ResponseEntity<>("Laboratory deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Laboratory not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/teacher/createLaboratory")
    public ResponseEntity<?> createLaboratory(@RequestBody LaboratoryDTO laboratoryDTO) {
        LaboratoryDTO createdLaboratory = laboratoryService.createLaboratory(laboratoryDTO);
        if (createdLaboratory == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdLaboratory, HttpStatus.OK);
    }

    @PostMapping("/teacher/editLaboratory")
    public ResponseEntity<?> editLaboratory(@RequestBody LaboratoryDTO laboratoryDTO) {
        LaboratoryDTO editedLaboratory = laboratoryService.editLaboratory(laboratoryDTO);
        if (editedLaboratory == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(editedLaboratory, HttpStatus.OK);
    }

    @GetMapping({"/teacher/retrieveLaboratories", "/student/retrieveLaboratories"})
    public ResponseEntity<?> retrieveLaboratories() {
        List<LaboratoryDTO> laboratoryDTOList = laboratoryService.retrieveAllLaboratories();
        if (laboratoryDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(laboratoryDTOList, HttpStatus.OK);
    }

}
