package com.example.bookTp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookTp.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long>{

}
