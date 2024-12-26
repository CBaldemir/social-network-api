package com.example.socialnetworkapi.like;

import com.example.socialnetworkapi.comment.Comment;
import com.example.socialnetworkapi.post.Post;
import com.example.socialnetworkapi.user.node.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("like")
public class Like {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "LIKED_BY", direction = Relationship.Direction.INCOMING)
    private User user;

    @Relationship(type = "LIKED_POST_BY", direction = Relationship.Direction.INCOMING)
    private Post post;

    @Relationship(type = "LIKED_COMMENT_BY", direction = Relationship.Direction.INCOMING)
    private Comment comment;

    @CreatedDate
    private LocalDateTime createdAt;
}