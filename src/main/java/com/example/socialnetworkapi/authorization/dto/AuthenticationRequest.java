package com.example.socialnetworkapi.authorization.dto;

import lombok.*;

@Data
@Builder
public class AuthenticationRequest {
    private String username;
    private String password;
}
