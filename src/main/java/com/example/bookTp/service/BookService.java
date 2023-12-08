package com.example.bookTp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookTp.model.Book;
import com.example.bookTp.repo.BookRepo;

@Service
public class BookService {
	
	@Autowired
	BookRepo repo;

	public List<Book> getAllBookItems() {
		ArrayList<Book> bookList = new ArrayList<>();
		repo.findAll().forEach(book -> bookList.add(book));
		
		return bookList;
	}
	
	public Book getBookItemById(Long id) {
		return repo.findById(id).get();
	}
	
/*	public boolean updateStatus(Long id) {
		Book book = getBookItemById(id);
		book.setStatus("Completed");
		
		return saveOrUpdateToDoItem(todo);
	}*/
	
	public boolean saveBookItem(Book book) {
		Book savedObj = repo.save(book);
		
		if (getBookItemById(savedObj.getId()) != null) {
			return true;
		}
		
		return false;
	}

	public boolean updateBookItem(Long id, Book book) {
	    // Check if the book with the given ID exists
	    if (repo.existsById(id)) {
	        // Set the ID of the book to be updated
	        book.setId(id);

	        // Save the updated book
	        Book updatedObj = repo.save(book);

	        // Check if the save operation was successful
	        return updatedObj != null;
	    }

	    // If the book with the given ID does not exist, return false
	    return false;
	}

	
	public boolean deleteBookItem(Long id) {
		repo.deleteById(id);
		
		if (repo.findById(id).isEmpty()) {
			return true;
		}
		
		return false;
	}
	
}
