package com.example.socialnetworkapi.comment.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.socialnetworkapi.like.node.Like;
import com.example.socialnetworkapi.like.repository.LikeRepository;
import org.springframework.stereotype.Component;

@Component
public class CommentMutationResolver implements GraphQLMutationResolver {
    private final LikeRepository likeRepository;

    public CommentMutationResolver(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Like create(Like like) {
        return likeRepository.save(like);
    }
}
