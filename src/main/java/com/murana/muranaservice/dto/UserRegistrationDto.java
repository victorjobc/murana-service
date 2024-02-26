package com.murana.muranaservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRegistrationDto {
    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;
}