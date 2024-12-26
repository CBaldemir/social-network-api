package com.example.socialnetworkapi.followed;

import com.example.socialnetworkapi.user.node.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("followed")
public class Followed {

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "IS_FOLLOWED_BY", direction = Relationship.Direction.INCOMING)
    private User user;
}
