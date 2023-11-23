package com.example.bookTp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    // Endpoint pour récupérer tous les livres
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    // Endpoint pour récupérer un livre par son ID
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        return books.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .orElse(null);
    }

    // Endpoint pour créer un nouveau livre
    @PostMapping
    public Book createBook(@RequestBody Book newBook) {
        newBook.setId((long) (books.size() + 1)); // ID simple pour cet exemple
        books.add(newBook);
        return newBook;
    }



    // Endpoint pour mettre à jour un livre existant
    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            Book existingBook = books.get(i);
            if (existingBook.getId().equals(bookId)) {
                updatedBook.setId(bookId);
                books.set(i, updatedBook);
                return updatedBook;
            }
        }
        return null;
    }

    // Endpoint pour supprimer un livre
    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        books.removeIf(book -> book.getId().equals(bookId));
    }
}
