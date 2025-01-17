package com.example.socialnetworkapi.comment.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.socialnetworkapi.comment.dto.CommentPage;
import com.example.socialnetworkapi.comment.node.Comment;
import com.example.socialnetworkapi.comment.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class CommentQueryResolver implements GraphQLQueryResolver {
    private final CommentRepository commentRepository;

    public CommentQueryResolver(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentPage getAllByUserPosts(int page, int size) {
        Page<Comment> commentPage = commentRepository.findAll(PageRequest.of(0, 1));
        return CommentPage.builder()
                .comments(commentPage.getContent())
                .totalCount((int) commentPage.getTotalElements())
                .hasNextPage(commentPage.hasNext())
                .hasPreviousPage(commentPage.hasPrevious())
                .build();
    }
}
