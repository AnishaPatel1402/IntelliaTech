package com.info.ordermangmentsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponseDTO {

    private Integer id;
    private String name;
    private String email;
}