package com.krasimirkolchev.examm.repositories;

import com.krasimirkolchev.examm.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
