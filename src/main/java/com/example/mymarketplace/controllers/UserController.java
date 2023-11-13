package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.User;
import com.example.mymarketplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User userFromDb = userRepository.findById(id).get();
        userFromDb.setLogin(user.getLogin());
        userFromDb.setName(user.getName());
        userFromDb.setPassword(user.getPassword());
        return userRepository.save(userFromDb);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return "User deleted";
        } catch (Exception e) {
            return "User not found";
        }
    }
}
