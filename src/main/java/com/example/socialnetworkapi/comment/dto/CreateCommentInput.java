package com.example.socialnetworkapi.comment.dto;

import lombok.Data;

@Data
public class CreateCommentInput {
    private String content;
    private Long userId;
    private Long postId;
}
