package com.info.leave_management.serviceimpl;

import com.info.leave_management.dto.LeaveRequestDto;
import com.info.leave_management.dto.LeaveResponseDto;
import com.info.leave_management.entity.Employee;
import com.info.leave_management.entity.Leave;
import com.info.leave_management.enums.LeaveStatus;
import com.info.leave_management.exception.BadRequestException;
import com.info.leave_management.exception.ResourceNotFoundException;
import com.info.leave_management.mapper.LeaveMapper;
import com.info.leave_management.repository.EmployeeRepository;
import com.info.leave_management.repository.LeaveRepository;
import com.info.leave_management.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveMapper leaveMapper;

    @Transactional
    @Override
    public LeaveResponseDto applyLeave(String employeeId, LeaveRequestDto dto) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + employeeId));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!username.equals(employee.getUser().getUsername())){
            throw new BadRequestException("User does not matched");
        }
//        System.out.println(username);

        if (dto.getStartDate() == null || dto.getEndDate() == null) {
            throw new BadRequestException("Start date and End date are required");
        }

        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new BadRequestException("Start date cannot be after end date");
        }

        if (dto.getEndDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Leave cannot end in the past");
        }

        if (dto.getStartDate().isBefore(java.time.LocalDate.now())) {
            throw new BadRequestException("Leave cannot be applied for past dates");
        }

        Leave leave = leaveMapper.toEntity(dto);

        leave.setEmployee(employee);
        leave.setStatus(LeaveStatus.PENDING);

        // calculate total days
        leave.calculateTotalDays();

        if (leave.getTotalDays() <= 0) {
            throw new BadRequestException("Invalid leave duration");
        }
        if (leave.getTotalDays() > 30) {
            throw new BadRequestException("Leave cannot exceed 30 days");
        }

        boolean exists = leaveRepository.existsOverlappingLeave(employeeId, dto.getStartDate(), dto.getEndDate());

        if (exists) {
            throw new BadRequestException("Leave already exists in selected date range");
        }

        Leave savedLeave = leaveRepository.save(leave);

        return leaveMapper.toDto(savedLeave);
    }


    @Override
    public LeaveResponseDto getLeaveById(String leaveId) {

        Leave leave = leaveRepository.findById(leaveId).orElseThrow(() ->
                        new ResourceNotFoundException("Leave not found with id: " + leaveId)
                );

        return leaveMapper.toDto(leave);
    }


    @Override
    public Page<LeaveResponseDto> getAllLeaves(Pageable pageable) {

        return leaveRepository.findAll(pageable).map(leave -> leaveMapper.toDto(leave));
    }


    @Override
    public Page<LeaveResponseDto> getLeavesByEmployee(String employeeId, Pageable pageable) {

        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }

        return leaveRepository.findByEmployeeId(employeeId, pageable).map(leave -> leaveMapper.toDto(leave));
    }


    @Transactional
    @Override
    public LeaveResponseDto updateLeaveStatus(String leaveId, LeaveStatus status) {

        Leave leave = leaveRepository.findById(leaveId).orElseThrow(() ->
                        new ResourceNotFoundException("Leave not found with id: " + leaveId)
                );

        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new BadRequestException("Only pending leaves can be updated");
        }

        leave.setStatus(status);
        Leave updatedLeave = leaveRepository.save(leave);

        return leaveMapper.toDto(updatedLeave);
    }

    @Override
    public Page<LeaveResponseDto> getLeavesByStatus(LeaveStatus status, Pageable pageable) {
        return leaveRepository.findByStatus(status, pageable).map(leave -> leaveMapper.toDto(leave));
    }

    @Override
    public long countLeavesByEmployeeAndStatus(String employeeId, LeaveStatus status) {
        return leaveRepository.countByEmployeeIdAndStatus(employeeId, status);
    }


    @Transactional
    @Override
    public LeaveResponseDto deleteLeave(String leaveId) {
        Leave leave = leaveRepository.findById(leaveId).orElseThrow(() -> new ResourceNotFoundException("Leave not found with id: " + leaveId));
        leaveRepository.delete(leave);
        return leaveMapper.toDto(leave);
    }
}