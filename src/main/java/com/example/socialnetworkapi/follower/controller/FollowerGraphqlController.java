package com.example.socialnetworkapi.follower.controller;

import com.example.socialnetworkapi.follower.dto.FollowerPage;
import com.example.socialnetworkapi.follower.node.Follower;
import com.example.socialnetworkapi.follower.repository.FollowerRepository;
import com.example.socialnetworkapi.post.dto.CreatePostInput;
import com.example.socialnetworkapi.user.node.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class FollowerGraphqlController {
    private final FollowerRepository followerRepository;

    public FollowerGraphqlController(FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    @QueryMapping
    public List<Follower> followers() {
        return followerRepository.findAll();
    }

    @QueryMapping
    public Optional<Follower> getFollowerById(Long id) {
        return followerRepository.findById(id);
    }

    @QueryMapping
    public FollowerPage allFollowers(int page, int size) {
        Page<Follower> followerPage = followerRepository.findAll(PageRequest.of(page, size));
        return FollowerPage.builder()
                .followers(followerPage.getContent())
                .totalCount((int) followerPage.getTotalElements())
                .hasNextPage(followerPage.hasNext())
                .hasPreviousPage(followerPage.hasPrevious())
                .build();
    }

    @MutationMapping
    public Follower createFollower(@Argument("input") CreatePostInput createFollowerInput) {
        Follower follower = new Follower();
        follower.setUser(new User());
        return followerRepository.save(follower);
    }
}
