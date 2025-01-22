package com.example.socialnetworkapi.like.controller;

import com.example.socialnetworkapi.like.dto.CreateLikeInput;
import com.example.socialnetworkapi.like.dto.LikePage;
import com.example.socialnetworkapi.like.node.Like;
import com.example.socialnetworkapi.like.repository.LikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class LikeGraphqlController {
    private final LikeRepository likeRepository;

    public LikeGraphqlController(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @QueryMapping
    public List<Like> likeList() {
        return likeRepository.findAll();
    }

    @QueryMapping
    public Optional<Like> getLikeById(Long id) {
        return likeRepository.findById(id);
    }

    @QueryMapping
    public LikePage allLikes(int page, int size) {
        Page<Like> likePage = likeRepository.findAll(PageRequest.of(page, size));
        return LikePage.builder()
                .likes(likePage.getContent())
                .totalCount((int) likePage.getTotalElements())
                .hasNextPage(likePage.hasNext())
                .hasPreviousPage(likePage.hasPrevious())
                .build();
    }

    @MutationMapping
    public Like createLike(@Argument("input") CreateLikeInput createLikeInput) {
        Like like = new Like();
        //like.setComment();
        //like.setPost();
        return likeRepository.save(like);
    }
}
