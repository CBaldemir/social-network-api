package com.example.socialnetworkapi.followed.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.socialnetworkapi.followed.node.Followed;
import com.example.socialnetworkapi.followed.repository.FollowedRepository;
import org.springframework.stereotype.Component;

@Component
public class FollowedMutationResolver implements GraphQLMutationResolver {
    private final FollowedRepository followedRepository;

    public FollowedMutationResolver(FollowedRepository followedRepository) {
        this.followedRepository = followedRepository;
    }

    public Followed create(Followed followed) {
        return followedRepository.save(followed);
    }
}
