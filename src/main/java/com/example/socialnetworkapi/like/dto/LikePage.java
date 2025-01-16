package com.example.socialnetworkapi.like.dto;

import com.example.socialnetworkapi.like.node.Like;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LikePage {
    private List<Like> likes;
    private int totalCount;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}
