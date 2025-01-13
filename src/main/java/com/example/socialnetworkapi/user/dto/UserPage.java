package com.example.socialnetworkapi.user.dto;

import com.example.socialnetworkapi.user.node.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserPage {
    private List<User> users;
    private int totalCount;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}
