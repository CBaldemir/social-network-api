package com.example.socialnetworkapi.user.node;

import com.example.socialnetworkapi.follewer.Follower;
import com.example.socialnetworkapi.followed.Followed;
import com.example.socialnetworkapi.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "username")
    private String username;

    @Property(name = "password")
    private String password;

    @Property(name = "email")
    private String email;

    @Property(name = "fullName")
    private String fullName;

    @Property(name = "bio")
    private String bio;

    @Property("profilePictureUrl")
    private String profilePictureUrl;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Relationship(type = "POSTED", direction = Relationship.Direction.OUTGOING)
    private List<Post> posts;

    @Relationship(type = "FOLLOWER", direction = Relationship.Direction.OUTGOING)
    private List<Follower> followers;

    @Relationship(type = "FOLLOWED", direction = Relationship.Direction.OUTGOING)
    private List<Followed> followedList;

}
