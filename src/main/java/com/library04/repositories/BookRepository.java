package com.library04.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library04.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query(value = "select book from Book book where book.title like %?1%")
	List<Book> findByTitle(String title);
	
	@Query(value = "select book from Book book where book.title like %?1%")
	Book findEntityByTitle(String title);
}
