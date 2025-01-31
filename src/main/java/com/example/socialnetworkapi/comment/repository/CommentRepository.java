package com.example.socialnetworkapi.comment.repository;

import com.example.socialnetworkapi.comment.node.Comment;
import com.example.socialnetworkapi.post.node.Post;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;

@GraphQlRepository
public interface CommentRepository extends Neo4jRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
