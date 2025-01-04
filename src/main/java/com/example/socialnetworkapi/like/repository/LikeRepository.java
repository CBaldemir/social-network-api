package com.example.socialnetworkapi.like.repository;

import com.example.socialnetworkapi.like.node.Like;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface LikeRepository extends Neo4jRepository<Like, Long> {
    List<Like> findAllBy();
}
