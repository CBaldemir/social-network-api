package com.example.socialnetworkapi.comment.node;

import com.example.socialnetworkapi.like.node.Like;
import com.example.socialnetworkapi.post.node.Post;
import com.example.socialnetworkapi.user.node.User;
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
@Node("comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "content")
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Relationship(type = "COMMENTED_BY", direction = Relationship.Direction.INCOMING)
    private User author;

    @Relationship(type = "COMMENT_ON", direction = Relationship.Direction.INCOMING)
    private Post post;

    @Relationship(type = "LIKED_COMMENT", direction = Relationship.Direction.OUTGOING)
    private List<Like> likes;
}
