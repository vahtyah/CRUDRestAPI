package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.Book;
import com.example.mymarketplace.models.Role;
import com.example.mymarketplace.models.User;
import com.example.mymarketplace.repositories.BookRepository;
import com.example.mymarketplace.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(Long id) {
        return ResponseEntity.ok(bookRepository.findById(id).get());
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
            return ResponseEntity.ok("Book deleted");
        } catch (Exception e) {
            return ResponseEntity.ok("Book not found");
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book bookFromDb = bookRepository.findById(id).get();
        bookFromDb.setAuthor(book.getAuthor());
        bookFromDb.setCost(book.getCost());
        bookFromDb.setName(book.getName());
        bookFromDb.setSeller(book.getSeller());
        bookFromDb.setType(book.getType());
        return ResponseEntity.ok(bookRepository.save(bookFromDb));
    }


    @GetMapping
    public ResponseEntity<String> getRole() {
        return ResponseEntity.ok("Hello Admin");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(Long id) {
        return ResponseEntity.ok(userRepository.findById(id).get());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User userFromDb = userRepository.findById(id).get();
        userFromDb.setLogin(user.getLogin());
        userFromDb.setName(user.getName());
        userFromDb.setPassword(user.getPassword());
        return ResponseEntity.ok(userRepository.save(userFromDb));
    }

    @PutMapping("/users/update_role/{id}")
    public ResponseEntity<User> updateUserRole(@PathVariable Long id) {
        User userFromDb = userRepository.findById(id).get();
        userFromDb.setRole(Role.SELLER);
        return ResponseEntity.ok(userRepository.save(userFromDb));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted");
        } catch (Exception e) {
            return ResponseEntity.ok("User not found");
        }
    }
}
