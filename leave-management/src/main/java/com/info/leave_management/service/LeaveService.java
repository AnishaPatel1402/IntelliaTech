package com.info.leave_management.service;

import com.info.leave_management.dto.LeaveRequestDto;
import com.info.leave_management.dto.LeaveResponseDto;
import com.info.leave_management.enums.LeaveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface LeaveService {
    LeaveResponseDto applyLeave(String employeeId, LeaveRequestDto dto);

    LeaveResponseDto getLeaveById(String leaveId);

    Page<LeaveResponseDto> getAllLeaves(Pageable pageable);

    Page<LeaveResponseDto> getLeavesByEmployee(String employeeId, Pageable pageable);

    LeaveResponseDto updateLeaveStatus(String leaveId, LeaveStatus status);

    Page<LeaveResponseDto> getLeavesByStatus(LeaveStatus status, Pageable pageable);

    long countLeavesByEmployeeAndStatus(String employeeId, LeaveStatus status);

    LeaveResponseDto deleteLeave(String leaveId);
}
