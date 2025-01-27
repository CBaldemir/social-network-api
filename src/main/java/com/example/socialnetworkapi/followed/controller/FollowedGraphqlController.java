package com.example.socialnetworkapi.followed.controller;

import com.example.socialnetworkapi.followed.dto.CreateFollowedInput;
import com.example.socialnetworkapi.followed.dto.FollowedPage;
import com.example.socialnetworkapi.followed.node.Followed;
import com.example.socialnetworkapi.followed.repository.FollowedRepository;
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
public class FollowedGraphqlController {
    private final FollowedRepository followedRepository;

    public FollowedGraphqlController(FollowedRepository followedRepository) {
        this.followedRepository = followedRepository;
    }

    @QueryMapping
    public List<Followed> followedList() {
        return followedRepository.findAll();
    }

    @QueryMapping
    public Optional<Followed> getFollowedById(Long id) {
        return followedRepository.findById(id);
    }

    @QueryMapping
    public FollowedPage allFollowedList(int page, int size) {
        Page<Followed> followedPage = followedRepository.findAll(PageRequest.of(page, size));
        return FollowedPage.builder()
                .followedList(followedPage.getContent())
                .totalCount((int) followedPage.getTotalElements())
                .hasNextPage(followedPage.hasNext())
                .hasPreviousPage(followedPage.hasPrevious())
                .build();
    }

    @MutationMapping
    public Followed createFollowed(@Argument("input") CreateFollowedInput createFollowedInput) {
        Followed followed = new Followed();
        followed.setUser(new User());
        return followedRepository.save(followed);
    }
}
