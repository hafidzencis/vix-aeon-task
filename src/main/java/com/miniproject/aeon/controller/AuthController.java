package com.miniproject.aeon.controller;

import com.miniproject.aeon.domain.dto.UsersDTO;
import com.miniproject.aeon.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping(value  ="/register" )
    ResponseEntity<?> register(@RequestBody UsersDTO req){
        return authService.register(req);
    }

    @PostMapping(value  ="/login" )
    ResponseEntity<?> login(@RequestBody UsersDTO req){
        return authService.authenticateAndGenerateToken(req);
    }
}
