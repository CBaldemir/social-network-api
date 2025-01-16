package com.example.socialnetworkapi.comment.dto;

import com.example.socialnetworkapi.comment.node.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommentPage {
    private List<Comment> comments;
    private int totalCount;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}
