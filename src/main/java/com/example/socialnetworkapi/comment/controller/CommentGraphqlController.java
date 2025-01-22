package com.example.socialnetworkapi.comment.controller;

import com.example.socialnetworkapi.comment.dto.CommentPage;
import com.example.socialnetworkapi.comment.dto.CreateCommentInput;
import com.example.socialnetworkapi.comment.node.Comment;
import com.example.socialnetworkapi.comment.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentGraphqlController {
    private final CommentRepository commentRepository;

    public CommentGraphqlController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @QueryMapping
    public List<Comment> comments() {
        return commentRepository.findAll();
    }

    @QueryMapping
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
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
        return commentRepository.save(comment);
    }
}
