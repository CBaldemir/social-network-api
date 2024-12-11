package com.example.socialnetworkapi.user.controller;

import com.example.socialnetworkapi.authorization.dto.AuthenticationRequest;
import com.example.socialnetworkapi.authorization.dto.AuthenticationResponse;
import com.example.socialnetworkapi.authorization.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserLoginController {
    private final AuthenticationService authenticationService;

    public UserLoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }
}
