package com.example.demo.service;

import com.example.demo.model.Attendance;
import com.example.demo.model.dto.AttendanceDTO;
import com.example.demo.model.dto.LaboratoryDTO;

import java.util.List;

public interface AttendanceService {

    void deleteAttendance(Integer id);

    AttendanceDTO createAttendance(AttendanceDTO attendanceDTO);

    AttendanceDTO editAttendance(AttendanceDTO attendanceDTO);

    List<AttendanceDTO> retrieveAllAttendances();

    List<AttendanceDTO> retrieveAttendancesForLaboratory(Integer laboratoryId);
}
