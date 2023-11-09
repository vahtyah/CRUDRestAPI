package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.Book;
import com.example.mymarketplace.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        books.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String name, @RequestParam String author, @RequestParam String type, @RequestParam String seller, @RequestParam Integer cost, Model model) {
        Book book = new Book(name, author, type, seller, cost);
        bookRepository.save(book);
        return getAllBooks(model);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).get();
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

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        try {
            bookRepository.deleteById(id);
            return getAllBooks(model);
        } catch (Exception e) {
            return "Book not found";
        }
    }
}
