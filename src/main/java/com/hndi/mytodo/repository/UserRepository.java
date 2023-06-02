package com.hndi.mytodo.repository;

import com.hndi.mytodo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> findByUserName(String username);
}
