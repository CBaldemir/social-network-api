package com.example.socialnetworkapi.followed.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.socialnetworkapi.followed.dto.FollowedPage;
import com.example.socialnetworkapi.followed.node.Followed;
import com.example.socialnetworkapi.followed.repository.FollowedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class FollowedQueryResolver implements GraphQLQueryResolver {
    private final FollowedRepository followedRepository;

    public FollowedQueryResolver(FollowedRepository followedRepository) {
        this.followedRepository = followedRepository;
    }

    public FollowedPage getAllByUserPosts(int page, int size) {
        Page<Followed> followedPage = followedRepository.findAll(PageRequest.of(0, 1));
        return FollowedPage.builder()
                .followedList(followedPage.getContent())
                .totalCount((int) followedPage.getTotalElements())
                .hasNextPage(followedPage.hasNext())
                .hasPreviousPage(followedPage.hasPrevious())
                .build();
    }
}
