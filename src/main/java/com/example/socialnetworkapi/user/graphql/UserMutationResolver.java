package com.example.socialnetworkapi.user.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.socialnetworkapi.user.node.User;
import com.example.socialnetworkapi.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMutationResolver implements GraphQLMutationResolver {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public UserMutationResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String password, String email, String fullName, String bio, String profilePictureUrl) {
        System.out.println("Inside createUser method");  // Debug log
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setFullName(fullName);
        user.setBio(bio);
        user.setProfilePictureUrl(profilePictureUrl);
        System.out.println("User before save: " + user);  // Log user object
        User savedUser = userRepository.save(user);
        System.out.println("Saved user: " + savedUser);  // Log saved user
        return savedUser;
    }
}
