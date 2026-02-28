package com.info.leave_management.controller;

import com.info.leave_management.dto.EmployeeRequestDto;
import com.info.leave_management.dto.EmployeeResponseDto;
import com.info.leave_management.service.EmployeeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable String id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<EmployeeResponseDto>> getAllEmployees(@ParameterObject Pageable pageable, @RequestParam(required = false) String department) {
        return ResponseEntity.ok(employeeService.getAllEmployees(pageable, department));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable String id, @RequestBody EmployeeRequestDto dto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeResponseDto> deleteEmployee(@PathVariable String id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/promote")
    public ResponseEntity<EmployeeResponseDto> promoteToAdmin(@PathVariable String id) {
        EmployeeResponseDto response = employeeService.promoteToAdmin(id);
        return ResponseEntity.ok(response);
    }
}
