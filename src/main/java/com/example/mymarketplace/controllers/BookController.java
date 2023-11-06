package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.Book;
import com.example.mymarketplace.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book bookFromDb = bookRepository.findById(id).get();
        bookFromDb.setAuthor(book.getAuthor());
        bookFromDb.setSeller(book.getSeller());
        bookFromDb.setType(book.getType());
        bookFromDb.setCost(book.getCost());
        bookFromDb.setName(book.getName());
        return bookRepository.save(bookFromDb);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        try {
            bookRepository.deleteById(id);
            return "Book deleted";
        } catch (Exception e) {
            return "Book not found";
        }
    }
}
