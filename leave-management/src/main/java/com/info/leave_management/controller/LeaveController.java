package com.info.leave_management.controller;


import com.info.leave_management.dto.LeaveRequestDto;
import com.info.leave_management.dto.LeaveResponseDto;
import com.info.leave_management.enums.LeaveStatus;
import com.info.leave_management.service.LeaveService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class LeaveController {

    private final LeaveService leaveService;


    @PostMapping("/apply/{employeeId}")
    public ResponseEntity<LeaveResponseDto> applyLeave(@PathVariable String employeeId, @Valid @RequestBody LeaveRequestDto dto) {
        LeaveResponseDto response = leaveService.applyLeave(employeeId, dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{leaveId}")
    public ResponseEntity<LeaveResponseDto> getLeaveById(@PathVariable String leaveId) {
        LeaveResponseDto response = leaveService.getLeaveById(leaveId);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<LeaveResponseDto>> getAllLeaves(@ParameterObject Pageable pageable) {
        Page<LeaveResponseDto> leaves = leaveService.getAllLeaves(pageable);
        return ResponseEntity.ok(leaves);
    }


    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Page<LeaveResponseDto>> getLeavesByEmployee(@PathVariable String employeeId,@ParameterObject Pageable pageable) {
        Page<LeaveResponseDto> leaves =
                leaveService.getLeavesByEmployee(employeeId, pageable);

        return ResponseEntity.ok(leaves);
    }

    //approve or reject leave
    @PatchMapping("/{leaveId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LeaveResponseDto> updateLeaveStatus(@PathVariable String leaveId, @RequestParam LeaveStatus status) {
        LeaveResponseDto response =
                leaveService.updateLeaveStatus(leaveId, status);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<LeaveResponseDto>> getLeavesByStatus(@RequestParam LeaveStatus status,@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(leaveService.getLeavesByStatus(status, pageable));
    }


    @GetMapping("/employee/{employeeId}/count")
    public ResponseEntity<Long> countLeavesByEmployeeAndStatus(@PathVariable String employeeId, @RequestParam LeaveStatus status) {
        return ResponseEntity.ok(leaveService.countLeavesByEmployeeAndStatus(employeeId, status));
    }


    @DeleteMapping("/{leaveId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LeaveResponseDto> deleteLeave(@PathVariable String leaveId) {
        LeaveResponseDto deletedLeave =  leaveService.deleteLeave(leaveId);
        return ResponseEntity.ok(deletedLeave);
    }
}