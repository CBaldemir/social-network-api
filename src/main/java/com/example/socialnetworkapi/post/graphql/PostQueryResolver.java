package com.example.socialnetworkapi.post.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.socialnetworkapi.post.dto.PostPage;
import com.example.socialnetworkapi.post.node.Post;
import com.example.socialnetworkapi.post.repository.PostRepository;
import com.example.socialnetworkapi.user.dto.UserPage;
import com.example.socialnetworkapi.user.node.User;
import com.example.socialnetworkapi.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class PostQueryResolver implements GraphQLQueryResolver {
    private final PostRepository postRepository;

    public PostQueryResolver(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostPage getAllByUserPosts(int page, int size) {
        Page<Post> postPage = postRepository.findAll(PageRequest.of(0, 1));
        return PostPage.builder()
                .posts(postPage.getContent())
                .totalCount((int) postPage.getTotalElements())
                .hasNextPage(postPage.hasNext())
                .hasPreviousPage(postPage.hasPrevious())
                .build();
    }
}
