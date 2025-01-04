package com.example.socialnetworkapi.followed.repository;

import com.example.socialnetworkapi.followed.node.Followed;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface FollowedRepository extends Neo4jRepository<Followed, Long> {
    List<Followed> findAllBy();
}
