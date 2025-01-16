package com.example.socialnetworkapi.like.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.socialnetworkapi.like.node.Like;
import com.example.socialnetworkapi.like.repository.LikeRepository;
import org.springframework.stereotype.Component;

@Component
public class LikeMutationResolver implements GraphQLMutationResolver {
    private final LikeRepository likeRepository;

    public LikeMutationResolver(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Like create(Like like) {
        return likeRepository.save(like);
    }
}
