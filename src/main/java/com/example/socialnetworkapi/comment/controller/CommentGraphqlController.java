package com.example.socialnetworkapi.comment.controller;

import com.example.socialnetworkapi.comment.dto.CommentPage;
import com.example.socialnetworkapi.comment.dto.CreateCommentInput;
import com.example.socialnetworkapi.comment.node.Comment;
import com.example.socialnetworkapi.comment.repository.CommentRepository;
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
public class CommentGraphqlController {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentGraphqlController(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @QueryMapping
    public List<Comment> comments() {
        return commentRepository.findAll();
    }

    @QueryMapping
    public List<Comment> getCommentByPostId(@Argument Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @QueryMapping
    public CommentPage allComments(int page, int size) {
        Page<Comment> commentPage = commentRepository.findAll(PageRequest.of(page, size));
        return CommentPage.builder()
                .comments(commentPage.getContent())
                .totalCount((int) commentPage.getTotalElements())
                .hasNextPage(commentPage.hasNext())
                .hasPreviousPage(commentPage.hasPrevious())
                .build();
    }

    @MutationMapping
    public Comment createComment(@Argument("input") CreateCommentInput createCommentInput) {
        Comment comment = new Comment();
        comment.setContent(createCommentInput.getContent());

        User user = userRepository.findById(createCommentInput.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(createCommentInput.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));

        comment.setAuthor(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }
}
