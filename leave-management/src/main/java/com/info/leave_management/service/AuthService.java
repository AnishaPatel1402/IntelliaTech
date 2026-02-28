package com.info.leave_management.service;


import com.info.leave_management.dto.RegisterRequestDto;
import com.info.leave_management.dto.RegisterResponseDto;

public interface AuthService {

    RegisterResponseDto register(RegisterRequestDto request);
}