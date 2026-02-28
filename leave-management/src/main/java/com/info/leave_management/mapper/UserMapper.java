package com.info.leave_management.mapper;


import com.info.leave_management.dto.RegisterRequestDto;
import com.info.leave_management.entity.User;
import com.info.leave_management.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(RegisterRequestDto request, PasswordEncoder passwordEncoder) {

        return User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
    }
}
