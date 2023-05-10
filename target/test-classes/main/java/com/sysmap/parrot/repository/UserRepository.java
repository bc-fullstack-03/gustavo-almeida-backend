package com.sysmap.parrot.repository;

import com.sysmap.parrot.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByName(String name);
    Optional<User> findUserById(UUID id);
}