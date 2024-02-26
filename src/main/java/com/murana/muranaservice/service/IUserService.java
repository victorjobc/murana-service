package com.murana.muranaservice.service;

import com.murana.muranaservice.dto.UserRegistrationDto;
import com.murana.muranaservice.entity.User;
import com.murana.muranaservice.exception.ValidationException;

public interface IUserService {
    User saveUser(UserRegistrationDto user) throws ValidationException;
}
