package com.info.leave_management.mapper;


import com.info.leave_management.dto.EmployeeResponseDto;
import com.info.leave_management.dto.RegisterRequestDto;
import com.info.leave_management.dto.RegisterResponseDto;
import com.info.leave_management.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(RegisterRequestDto request) {

        return Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .department(request.getDepartment())
                .designation(request.getDesignation())
                .build();
    }

    public EmployeeResponseDto toDto(Employee employee) {

        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .designation(employee.getDesignation())
                .createdAt(employee.getCreatedAt())
                .build();
    }
}