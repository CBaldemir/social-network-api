package com.example.socialnetworkapi.like.dto;

import lombok.Data;

@Data
public class CreateLikeInput {
    private Long userId;
    private Long commentId;
    private Long postId;
}
