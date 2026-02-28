package com.info.leave_management.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class EmployeeResponseDto {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String department;

    private String designation;

    private LocalDateTime createdAt;
}
