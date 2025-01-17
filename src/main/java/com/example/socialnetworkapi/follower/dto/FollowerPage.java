package com.example.socialnetworkapi.follower.dto;

import com.example.socialnetworkapi.follower.node.Follower;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FollowerPage {
    private List<Follower> followers;
    private int totalCount;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}
