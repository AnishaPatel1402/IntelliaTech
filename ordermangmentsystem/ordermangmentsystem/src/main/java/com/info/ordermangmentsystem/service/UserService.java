package com.info.ordermangmentsystem.service;

import com.info.ordermangmentsystem.dto.UserRequestDTO;
import com.info.ordermangmentsystem.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO request);

    UserResponseDTO getUserById(Integer id);

    List<UserResponseDTO> getAllUsers();
}

