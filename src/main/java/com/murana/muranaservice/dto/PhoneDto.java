package com.murana.muranaservice.dto;

import lombok.Data;

@Data
public class PhoneDto {
    private String number;
    private String cityCode;
    private String countryCode;
}