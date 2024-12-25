package com.example.socialnetworkapi.user.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.time.LocalDateTime;

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
}
