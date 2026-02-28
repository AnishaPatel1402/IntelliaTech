package com.info.leave_management.serviceimpl;

import com.info.leave_management.dto.RegisterRequestDto;
import com.info.leave_management.dto.RegisterResponseDto;
import com.info.leave_management.entity.Employee;
import com.info.leave_management.entity.User;
import com.info.leave_management.enums.Role;
import com.info.leave_management.exception.UserNotFoundException;
import com.info.leave_management.mapper.EmployeeMapper;
import com.info.leave_management.mapper.UserMapper;
import com.info.leave_management.repository.EmployeeRepository;
import com.info.leave_management.repository.UserRepository;
import com.info.leave_management.service.AuthService;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class AuthServiceImpl implements AuthService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeMapper employeeMapper;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserNotFoundException("User not found with username "+ request.getUsername());
        }

        if (employeeRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserNotFoundException("Employee already exists with email "+ request.getEmail());
        }


        Employee employee = employeeMapper.toEntity(request);
        employeeRepository.save(employee);


        User user = userMapper.toUser(request, passwordEncoder);
        user.setEmployee(employee);
        userRepository.save(user);

        return RegisterResponseDto.builder()
                .employeeId(employee.getId())
                .username(user.getUsername())
                .role(user.getRole().name())
                .message("User registered successfully")
                .build();
    }
}
