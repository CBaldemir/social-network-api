package com.example.socialnetworkapi.follower.repository;

import com.example.socialnetworkapi.follower.node.Follower;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface FollowerRepository extends Neo4jRepository<Follower, Long> {
}
