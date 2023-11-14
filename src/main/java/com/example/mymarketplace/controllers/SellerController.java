package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.Book;
import com.example.mymarketplace.models.User;
import com.example.mymarketplace.repositories.BookRepository;
import com.example.mymarketplace.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping
    public ResponseEntity<String> getRole() {
        return ResponseEntity.ok("Hello Seller");
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(Long id) {
        return ResponseEntity.ok(bookRepository.findById(id).get());
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
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

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
            return ResponseEntity.ok("Book deleted");
        } catch (Exception e) {
            return ResponseEntity.ok("Book not found");
        }
    }

}
