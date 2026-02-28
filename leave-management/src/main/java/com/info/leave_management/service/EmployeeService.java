package com.info.leave_management.service;

import com.info.leave_management.dto.EmployeeRequestDto;
import com.info.leave_management.dto.EmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EmployeeService {

    EmployeeResponseDto getEmployeeById(String id);

    Page<EmployeeResponseDto> getAllEmployees(Pageable pageable, String department);

    EmployeeResponseDto updateEmployee(String id, EmployeeRequestDto dto);

    EmployeeResponseDto deleteEmployee(String id);

    EmployeeResponseDto promoteToAdmin(String employeeId);
}