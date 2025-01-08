package com.example.socialnetworkapi.comment.repository;

import com.example.socialnetworkapi.comment.node.Comment;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface CommentRepository extends Neo4jRepository<Comment, Long> {
    List<Comment> findAllBy();
}
