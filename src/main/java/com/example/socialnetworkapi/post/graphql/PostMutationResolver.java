package com.example.socialnetworkapi.post.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.socialnetworkapi.post.node.Post;
import com.example.socialnetworkapi.post.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class PostMutationResolver implements GraphQLMutationResolver {
    private final PostRepository postRepository;

    public PostMutationResolver(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post create(Post post) {
        return postRepository.save(post);
    }
}
