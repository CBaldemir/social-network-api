package com.example.socialnetworkapi.post.controller;

import com.example.socialnetworkapi.post.dto.CreatePostInput;
import com.example.socialnetworkapi.post.dto.PostPage;
import com.example.socialnetworkapi.post.node.Post;
import com.example.socialnetworkapi.post.repository.PostRepository;
import com.example.socialnetworkapi.user.node.User;
import com.example.socialnetworkapi.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostGraphqlController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostGraphqlController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @QueryMapping
    public List<Post> posts() {
        return postRepository.findAll();
    }

    @QueryMapping
    public List<Post> getPostByAuthorId(@Argument Long userId) {
        return postRepository.findByAuthorId(userId);
    }

    @QueryMapping
    public List<Post> getPostByTitleContaining(@Argument String title) {
        return postRepository.findByTitleContaining(title);
    }

    @QueryMapping
    public PostPage allPosts(int page, int size) {
        Page<Post> postPage = postRepository.findAll(PageRequest.of(page, size));
        return PostPage.builder()
                .posts(postPage.getContent())
                .totalCount((int) postPage.getTotalElements())
                .hasNextPage(postPage.hasNext())
                .hasPreviousPage(postPage.hasPrevious())
                .build();
    }

    @MutationMapping
    public Post createPost(@Argument("input") CreatePostInput createPostInput) {
        Post post = new Post();
        post.setTitle(createPostInput.getTitle());
        post.setContent(createPostInput.getContent());
        User user = userRepository.findById(createPostInput.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        post.setAuthor(user);
        return postRepository.save(post);
    }
}