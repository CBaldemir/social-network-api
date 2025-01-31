package com.example.socialnetworkapi.post.dto;

import lombok.Data;

@Data
public class CreatePostInput {
    private String title;
    private String content;
    private Long userId;
}
