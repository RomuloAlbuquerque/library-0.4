package com.library04.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library04.dtos.BookDTO;
import com.library04.entities.Book;
import com.library04.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	@Transactional
	public BookDTO create(BookDTO dto) {
		Book entity = new Book();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new BookDTO(entity);
	}

	@Transactional
	public List<BookDTO> read() {
		List<Book> entityList = new ArrayList<>();
		entityList = repository.findAll();
		List<BookDTO> dtoList = new ArrayList<>();
		for (Book entity : entityList) {
			BookDTO dto = new BookDTO(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Transactional
	public BookDTO update(Long id, BookDTO dto) {
		Book entity = repository.getById(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new BookDTO(entity);
	}

	@Transactional
	public BookDTO updateByTitle(String title, BookDTO dto) {
		Book entity = repository.findEntityByTitle(title);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new BookDTO(entity);
	}
	
	@Transactional
	public BookDTO findEntityByTitle(String title) {
		Book entity = repository.findEntityByTitle(title);
		return new BookDTO(entity);
	}

	@Transactional
	public List<BookDTO> findByTitle(String title) {
		List<Book> entityList = new ArrayList<>();
		entityList = repository.findByTitle(title);
		List<BookDTO> dtoList = new ArrayList<>();
		for (Book entity : entityList) {
			BookDTO dto = new BookDTO(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Transactional
	public BookDTO findById(Long id) {
		Optional<Book> obj = repository.findById(id);
		Book entity = obj.get();
		return new BookDTO(entity);
	}

	public void delete(String title) {
		Book entity = repository.findEntityByTitle(title);
		repository.deleteById(entity.getId());
	}

	private void copyDtoToEntity(BookDTO dto, Book entity) {
		entity.setTitle(dto.getTitle());
		entity.setAuthor(dto.getAuthor());
		entity.setDescription(dto.getDescription());
		entity.setCompany(dto.getCompany());
		entity.setBy(dto.getBy());
	}

}
