package com.example.demo.controller;

import com.example.demo.model.dto.AttendanceDTO;
import com.example.demo.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/teacher/retrieveAllAttendances")
    private ResponseEntity<?> retrieveAllAttendances() {
        List<AttendanceDTO> attendanceDTOList = attendanceService.retrieveAllAttendances();
        if (attendanceDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(attendanceDTOList, HttpStatus.OK);
    }

    @GetMapping("/teacher/retrieveAttendancesForLaboratory")
    private ResponseEntity<?> retrieveAttendancesForLaboratory(@RequestBody Integer laboratoryId) {
        List<AttendanceDTO> attendanceDTOList = attendanceService.retrieveAttendancesForLaboratory(laboratoryId);
        if (attendanceDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(attendanceDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/teacher/deleteAttendanceById")
    private ResponseEntity<?> deleteAttendanceById(@RequestBody Integer id) {
        try {
            attendanceService.deleteAttendance(id);
            return new ResponseEntity<>("Attendance deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Attendance not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/teacher/createAttendance")
    private ResponseEntity<?> createAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO createdAttendance = attendanceService.createAttendance(attendanceDTO);
        if (createdAttendance == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdAttendance, HttpStatus.OK);
    }

    @PostMapping("/teacher/editAttendance")
    private ResponseEntity<?> editAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO editedAttendance = attendanceService.editAttendance(attendanceDTO);
        if (editedAttendance == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(editedAttendance, HttpStatus.OK);
    }
}
