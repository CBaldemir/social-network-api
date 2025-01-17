package com.example.socialnetworkapi.post.node;

import com.example.socialnetworkapi.comment.node.Comment;
import com.example.socialnetworkapi.like.node.Like;
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
@Node("post")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "title")
    private String title;

    @Property(name = "content")
    private String content;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    @Relationship(type = "POSTED_BY", direction = Relationship.Direction.INCOMING)
    private User user;

    @Relationship(type = "COMMENTED", direction = Relationship.Direction.OUTGOING)
    private List<Comment> comments;

    @Relationship(type = "LIKED_POST", direction = Relationship.Direction.OUTGOING)
    private List<Like> likes;
}
