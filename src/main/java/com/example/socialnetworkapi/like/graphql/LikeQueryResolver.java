package com.example.socialnetworkapi.like.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.socialnetworkapi.like.dto.LikePage;
import com.example.socialnetworkapi.like.node.Like;
import com.example.socialnetworkapi.like.repository.LikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class LikeQueryResolver implements GraphQLQueryResolver {
    private final LikeRepository likeRepository;

    public LikeQueryResolver(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public LikePage getAllByUserPosts(int page, int size) {
        Page<Like> likePage = likeRepository.findAll(PageRequest.of(0, 1));
        return LikePage.builder()
                .likes(likePage.getContent())
                .totalCount((int) likePage.getTotalElements())
                .hasNextPage(likePage.hasNext())
                .hasPreviousPage(likePage.hasPrevious())
                .build();
    }
}
