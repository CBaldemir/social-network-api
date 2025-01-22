package com.example.socialnetworkapi.post.repository;

import com.example.socialnetworkapi.post.node.Post;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;

@GraphQlRepository
public interface PostRepository extends Neo4jRepository<Post, Long> {
    List<Post> findByTitleContaining(String title);
}
