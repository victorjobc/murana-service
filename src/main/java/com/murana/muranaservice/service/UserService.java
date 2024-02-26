package com.murana.muranaservice.service;

import com.murana.muranaservice.configuration.TokenProvider;
import com.murana.muranaservice.configuration.UserProperties;
import com.murana.muranaservice.dto.UserRegistrationDto;
import com.murana.muranaservice.entity.Phone;
import com.murana.muranaservice.entity.User;
import com.murana.muranaservice.exception.ValidationException;
import com.murana.muranaservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private TokenProvider tokenProvider;
    private UserProperties userProperties;

    public User saveUser(UserRegistrationDto userRegistrationDto) throws ValidationException {
        // Validar datos de entrada
        if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
            throw new ValidationException("El correo ya registrado");
        }
        if(!userRegistrationDto.getEmail().matches(userProperties.getEmailRegex())){
            throw new ValidationException("Formato de correo incorrecto");
        }
        if(!userRegistrationDto.getPassword().matches(userProperties.getPasswordRegex())){
            throw new ValidationException("Formato de clave incorrecto");
        }

        // Crear el usuario
        User user = new User(userRegistrationDto.getName(),
                userRegistrationDto.getEmail(),
                encoder.encode(userRegistrationDto.getPassword()));

        // Agregar teléfonos
        userRegistrationDto.getPhones().forEach(phoneDto -> user.addPhone(new Phone(phoneDto.getNumber(), phoneDto.getCityCode(), phoneDto.getCountryCode())));

        // Generar token
        String token = tokenProvider.createToken(user);
        user.setToken(token);
        return userRepository.save(user);
    }

}