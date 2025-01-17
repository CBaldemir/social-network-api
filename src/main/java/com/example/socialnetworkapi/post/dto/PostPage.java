package com.example.socialnetworkapi.post.dto;

import com.example.socialnetworkapi.post.node.Post;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostPage {
    private List<Post> posts;
    private int totalCount;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}
