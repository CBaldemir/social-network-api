package com.example.socialnetworkapi.user.dto;

import lombok.Data;

@Data
public class CreateUserInput {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String bio;
    private String profilePictureUrl;
}
