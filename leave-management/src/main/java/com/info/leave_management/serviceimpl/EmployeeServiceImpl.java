package com.info.leave_management.serviceimpl;

import com.info.leave_management.dto.EmployeeRequestDto;
import com.info.leave_management.dto.EmployeeResponseDto;
import com.info.leave_management.entity.Employee;
import com.info.leave_management.enums.Role;
import com.info.leave_management.exception.BadRequestException;
import com.info.leave_management.exception.ResourceNotFoundException;
import com.info.leave_management.exception.UserNotFoundException;
import com.info.leave_management.mapper.EmployeeMapper;
import com.info.leave_management.repository.EmployeeRepository;
import com.info.leave_management.service.EmployeeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDto getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return employeeMapper.toDto(employee);
    }

    @Override
    public Page<EmployeeResponseDto> getAllEmployees(Pageable pageable, String department) {
        Page<Employee> employees;

        if (department != null && !department.trim().isEmpty()) {
            employees = employeeRepository.findByDepartmentIgnoreCase(pageable,department);
        } else {
            employees = employeeRepository.findAll(pageable);
        }

        return employees.map(employee -> employeeMapper.toDto(employee));
    }

    @Transactional
    @Override
    public EmployeeResponseDto updateEmployee(String id, EmployeeRequestDto dto) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setDepartment(dto.getDepartment());
        employee.setDesignation(dto.getDesignation());

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Transactional
    @Override
    public EmployeeResponseDto deleteEmployee(String id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        EmployeeResponseDto response = employeeMapper.toDto(employee);

        employeeRepository.delete(employee);

        return response;
    }

    @Transactional
    @Override
    public EmployeeResponseDto promoteToAdmin(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + employeeId)
                );

        if (employee.getUser().getRole() == Role.ADMIN) {
            throw new BadRequestException("Employee is already an ADMIN");
        }

        employee.getUser().setRole(Role.ADMIN);

        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toDto(updatedEmployee);
    }
}
