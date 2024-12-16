package com.example.socialnetworkapi.user.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.time.LocalDateTime;

@Data
@Entity
@Node("user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
