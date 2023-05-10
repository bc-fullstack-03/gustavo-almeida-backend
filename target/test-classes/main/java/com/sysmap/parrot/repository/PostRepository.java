package com.sysmap.parrot.repository;

import com.sysmap.parrot.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends MongoRepository<Post, UUID> {

    Optional<Post> findPostById(UUID id);
    List<Post> findPostsByUserId(UUID userId);



}
