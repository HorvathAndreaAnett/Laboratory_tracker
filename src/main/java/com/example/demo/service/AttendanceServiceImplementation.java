package com.example.demo.service;

import com.example.demo.model.Attendance;
import com.example.demo.model.dto.AttendanceDTO;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.LaboratoryRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImplementation implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Override
    public void deleteAttendance(Integer id) {
        attendanceRepository.deleteById(id);
    }

    @Override
    public AttendanceDTO createAttendance(AttendanceDTO attendanceDTO) {
        Attendance attendance = new Attendance();
        attendance.setUser(userRepository.findById(attendanceDTO.getUserId()).orElse(null));
        attendance.setLaboratory(laboratoryRepository.findById(attendanceDTO.getLaboratoryId()).orElse(null));

        attendance = attendanceRepository.save(attendance);
        return AttendanceDTO.fromEntity(attendance);
    }

    @Override
    public AttendanceDTO editAttendance(AttendanceDTO attendanceDTO) {
        Attendance attendance = attendanceRepository.findById(attendanceDTO.getId()).orElse(null);
        if (attendance == null) {
            return null;
        }
        attendance.setUser(userRepository.findById(attendanceDTO.getUserId()).orElse(null));
        attendance.setLaboratory(laboratoryRepository.findById(attendanceDTO.getLaboratoryId()).orElse(null));

        attendance = attendanceRepository.save(attendance);
        return AttendanceDTO.fromEntity(attendance);
    }

    @Override
    public List<AttendanceDTO> retrieveAllAttendances() {
        return attendanceRepository.findAll().stream().map(AttendanceDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> retrieveAttendancesForLaboratory(Integer laboratoryId) {
        return attendanceRepository.findAll()
                .stream()
                .filter(a -> a.getLaboratory().getId().equals(laboratoryId))
                .map(AttendanceDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
