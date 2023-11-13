package com.example.mymarketplace.repositories;

import com.example.mymarketplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
