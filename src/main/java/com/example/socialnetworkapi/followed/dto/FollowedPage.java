package com.example.socialnetworkapi.followed.dto;

import com.example.socialnetworkapi.followed.node.Followed;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FollowedPage {
    private List<Followed> followedList;
    private int totalCount;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}
