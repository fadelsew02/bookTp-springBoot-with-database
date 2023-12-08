package com.example.bookTp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bookTp.model.Book;
import com.example.bookTp.service.BookService;

import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/books")

public class BookController {

	@Autowired
	private BookService service;
	private List<Book> books = new ArrayList<>();

	// Endpoint pour récupérer tous les livres
	@GetMapping
	public List<Book> seeAllBooks() {
		books = service.getAllBookItems();
		return books;
	}

	// Endpoint pour récupérer un livre par son ID
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
    	return service.getBookItemById(bookId);
    }

    // Endpoint pour créer un nouveau livre
    @PostMapping
    public boolean createBook(@RequestBody Book newBook) {
    	if(service.saveBookItem(newBook)){
    		return true;
    	} else {
    		return false;
    	}
    }

    // Endpoint pour supprimer un livre
    @DeleteMapping("/{bookId}")
    public boolean deleteBook(@PathVariable Long bookId) {
        if(service.deleteBookItem(bookId)) {
        	return true;
        } else {
        	return false;
        }
    }

    // Endpoint pour mettre à jour un livre existant
    @PutMapping("/{bookId}")
    public String updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        if(service.updateBookItem(bookId, updatedBook)){
        	return "Book updated";
        } else {
        	return "Book not updated";
        }
        
    }
}

