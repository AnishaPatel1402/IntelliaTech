package com.info.leave_management.repository;


import com.info.leave_management.entity.Leave;
import com.info.leave_management.enums.LeaveStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, String> {

    Page<Leave> findByEmployeeId(String employeeId, Pageable pageable);

    List<Leave> findByEmployeeId(String employeeId);

    Page<Leave> findByStatus(LeaveStatus status, Pageable pageable);

    long countByEmployeeIdAndStatus(String employeeId, LeaveStatus status);

    @Query("""
           SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END
           FROM Leave l
           WHERE l.employee.id = :employeeId
           AND l.status <> 'REJECTED'
           AND (
                l.startDate <= :endDate
                AND l.endDate >= :startDate
           )""")
    boolean existsOverlappingLeave(@Param("employeeId") String employeeId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}