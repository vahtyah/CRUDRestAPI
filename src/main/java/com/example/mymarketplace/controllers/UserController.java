package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.Book;
import com.example.mymarketplace.models.Role;
import com.example.mymarketplace.models.User;
import com.example.mymarketplace.repositories.BookRepository;
import com.example.mymarketplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<String> getRole() {
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(Long id) {
        return ResponseEntity.ok(bookRepository.findById(id).get());
    }

    @PutMapping("/update_role/{id}")
    public ResponseEntity<User> updateUserRole(@PathVariable Long id) {
        User userFromDb = userRepository.findById(id).get();
        userFromDb.setRole(Role.SELLER);
        return ResponseEntity.ok(userRepository.save(userFromDb));
    }
}
