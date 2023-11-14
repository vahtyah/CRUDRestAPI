package com.example.mymarketplace.repositories;

import com.example.mymarketplace.models.Role;
import com.example.mymarketplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    User findById(long id);
    User findByRole(Role role);
}
