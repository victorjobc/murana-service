package com.murana.muranaservice.controller;

import com.murana.muranaservice.dto.UserRegistrationDto;
import com.murana.muranaservice.dto.response.MessageResponse;
import com.murana.muranaservice.dto.response.UserResponse;
import com.murana.muranaservice.entity.User;
import com.murana.muranaservice.exception.ValidationException;
import com.murana.muranaservice.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private IUserService userService;



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        try{
            // Guardar el usuario en la base de datos
            User savedUser = userService.saveUser(userRegistrationDto);

            // Retornar la respuesta
            return ResponseEntity.ok(
                    new UserResponse(
                            savedUser.getId(),
                            savedUser.getCreated(),
                            savedUser.getModified(),
                            savedUser.getLastLogin(),
                            savedUser.getToken(),
                            savedUser.getIsActive()
                    )
            );
        } catch (ValidationException e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

    }
}