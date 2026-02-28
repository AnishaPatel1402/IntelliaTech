package com.info.leave_management.config;

import com.info.leave_management.entity.Employee;
import com.info.leave_management.entity.User;
import com.info.leave_management.enums.Role;
import com.info.leave_management.repository.EmployeeRepository;
import com.info.leave_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) {

        if (userRepository.countByRole(Role.ADMIN) == 0) {

            Employee emp = Employee.builder()
                    .firstName("Admin")
                    .lastName("User")
                    .email("admin@gmail.com")
                    .department("Management")
                    .designation("Administrator")
                    .build();

            employeeRepository.save(emp);

            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin1234"))
                    .role(Role.ADMIN)
                    .employee(emp)
                    .build();


            emp.setUser(admin);
            userRepository.save(admin);

            System.out.println("Default ADMIN created successfully with Employee");
        }
    }
}