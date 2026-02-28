package com.info.leave_management.repository;

import com.info.leave_management.entity.User;
import com.info.leave_management.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Integer countByRole(Role role);
}
