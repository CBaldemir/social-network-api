package com.example.socialnetworkapi.follower.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.socialnetworkapi.like.node.Like;
import com.example.socialnetworkapi.like.repository.LikeRepository;
import org.springframework.stereotype.Component;

@Component
public class FollowerMutationResolver implements GraphQLMutationResolver {
    private final LikeRepository likeRepository;

    public FollowerMutationResolver(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Like create(Like like) {
        return likeRepository.save(like);
    }
}
