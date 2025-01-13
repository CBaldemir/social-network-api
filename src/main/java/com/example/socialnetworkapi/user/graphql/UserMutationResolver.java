package com.example.socialnetworkapi.user.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.socialnetworkapi.user.node.User;
import com.example.socialnetworkapi.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMutationResolver implements GraphQLMutationResolver {
    private final UserRepository userRepository;

    public UserMutationResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }
}
