package com.example.socialnetworkapi.user.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.socialnetworkapi.user.dto.UserPage;
import com.example.socialnetworkapi.user.node.User;
import com.example.socialnetworkapi.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {
    private final UserRepository userRepository;

    public UserQueryResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserPage getAllUsers(int page, int size) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(0, 1));
        return UserPage.builder()
                .users(userPage.getContent())
                .totalCount((int) userPage.getTotalElements())
                .hasNextPage(userPage.hasNext())
                .hasPreviousPage(userPage.hasPrevious())
                .build();
    }
}
