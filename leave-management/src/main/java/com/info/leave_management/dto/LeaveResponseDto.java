package com.info.leave_management.dto;

import com.info.leave_management.enums.LeaveStatus;
import com.info.leave_management.enums.LeaveType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class LeaveResponseDto {

    private String id;

    private LeaveType leaveType;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long totalDays;

    private LeaveStatus status;

    private String employeeId;
}
