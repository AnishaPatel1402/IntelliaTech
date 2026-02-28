package com.info.leave_management.mapper;

import com.info.leave_management.dto.LeaveRequestDto;
import com.info.leave_management.dto.LeaveResponseDto;
import com.info.leave_management.entity.Leave;
import com.info.leave_management.enums.LeaveStatus;
import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {

    public Leave toEntity(LeaveRequestDto dto) {

        return Leave.builder()
                .leaveType(dto.getLeaveType())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(LeaveStatus.PENDING)
                .build();
    }

    public LeaveResponseDto toDto(Leave leave) {

        return LeaveResponseDto.builder()
                .id(leave.getId())
                .leaveType(leave.getLeaveType())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .totalDays(leave.getTotalDays())
                .status(leave.getStatus())
                .employeeId(leave.getEmployee().getId())
                .build();
    }
}