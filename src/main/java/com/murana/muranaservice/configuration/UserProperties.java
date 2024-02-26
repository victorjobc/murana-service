package com.murana.muranaservice.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserProperties {
    @Value("${user.email.regex}")
    private String emailRegex;

    @Value("${user.password.regex}")
    private String passwordRegex;

}