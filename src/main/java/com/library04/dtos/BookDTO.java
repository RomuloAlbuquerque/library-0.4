package com.library04.dtos;

import com.library04.entities.Book;

public class BookDTO {
	
	private Long id;
	private String title;
	private String author;
	private String description;
	private String company;
	private Long by;
	
	public BookDTO() {
		
	}

	public BookDTO(Long id, String title, String author, String description, String company, Long by) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.company = company;
		this.by = by;
	}
	
	public BookDTO(Book entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.author = entity.getAuthor();
		this.description = entity.getDescription();
		this.company = entity.getCompany();
		this.by = entity.getBy();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Long getBy() {
		return by;
	}

	public void setBy(Long by) {
		this.by = by;
	}

}
