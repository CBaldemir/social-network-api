package com.example.socialnetworkapi.authorization.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationResponse {
    private String userName;
    private String token;
}
