package com.example.socialnetworkapi.authorization.service;

import com.example.socialnetworkapi.authorization.dto.AuthenticationRequest;
import com.example.socialnetworkapi.authorization.dto.AuthenticationResponse;
import com.example.socialnetworkapi.user.node.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        );

        final Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        final UserPrincipal userPrincipal = (UserPrincipal) authenticate.getPrincipal();

        return AuthenticationResponse.builder()
                .userName(userPrincipal.getUsername())
                .token(jwtService.generateToken(userPrincipal))
                .build();
    }
}
