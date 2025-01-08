package com.example.socialnetworkapi.follower.node;

import com.example.socialnetworkapi.user.node.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("follower")
public class Follower {

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "IS_FOLLOWER_BY", direction = Relationship.Direction.INCOMING)
    private User user;
}
