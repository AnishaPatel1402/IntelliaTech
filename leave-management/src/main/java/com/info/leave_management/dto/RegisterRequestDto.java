package com.info.leave_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {

    @NotBlank(message = "firstName field can not be blank")
    private String firstName;

    @NotBlank(message = "lastName field can not be blank")
    private String lastName;

    @Email(message = "Invalid email formate")

    @NotBlank(message = "email field can not be blank")
    private String email;

    @NotBlank(message = "department field can not be blank")
    private String department;

    @NotBlank(message = "designation field can not be blank")
    private String designation;

    @NotBlank(message = "username field can not be blank")
    private String username;

    @Size(min = 4, message = "password must contain at least 4 characters")
    private String password;
}
