package com.example.socialnetworkapi.user.controller;

import com.example.socialnetworkapi.user.dto.CreateUserInput;
import com.example.socialnetworkapi.user.dto.UserPage;
import com.example.socialnetworkapi.user.node.User;
import com.example.socialnetworkapi.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserGraphqlController {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;

    public UserGraphqlController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryMapping
    public List<User> users() {
        return userRepository.findAll();
    }

    @QueryMapping
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @QueryMapping
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @QueryMapping
    public UserPage allUsers(int page, int size) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, size));
        return UserPage.builder()
                .users(userPage.getContent())
                .totalCount((int) userPage.getTotalElements())
                .hasNextPage(userPage.hasNext())
                .hasPreviousPage(userPage.hasPrevious())
                .build();
    }

    @MutationMapping
    public User createUser(@Argument("input") CreateUserInput createUserInput) {
        User user = new User();
        user.setUsername(createUserInput.getUsername());
        user.setPassword(passwordEncoder.encode(createUserInput.getPassword()));
        user.setEmail(createUserInput.getEmail());
        user.setFullName(createUserInput.getFullName());
        user.setBio(createUserInput.getBio());
        user.setProfilePictureUrl(createUserInput.getProfilePictureUrl());
        return userRepository.save(user);
    }
}
