package com.example.socialnetworkapi.follower.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.socialnetworkapi.follower.dto.FollowerPage;
import com.example.socialnetworkapi.follower.node.Follower;
import com.example.socialnetworkapi.follower.repository.FollowerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class FollowerQueryResolver implements GraphQLQueryResolver {
    private final FollowerRepository followerRepository;

    public FollowerQueryResolver(FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    public FollowerPage getAllByUserPosts(int page, int size) {
        Page<Follower> followerPage = followerRepository.findAll(PageRequest.of(0, 1));
        return FollowerPage.builder()
                .followers(followerPage.getContent())
                .totalCount((int) followerPage.getTotalElements())
                .hasNextPage(followerPage.hasNext())
                .hasPreviousPage(followerPage.hasPrevious())
                .build();
    }
}
