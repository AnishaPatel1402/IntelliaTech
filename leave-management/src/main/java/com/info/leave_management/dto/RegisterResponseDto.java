package com.info.leave_management.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class RegisterResponseDto {
    private String employeeId;
    private String username;
    private String role;
    private String message;
}