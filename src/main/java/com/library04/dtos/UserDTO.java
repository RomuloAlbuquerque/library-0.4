package com.library04.dtos;

import com.library04.entities.User;

public class UserDTO {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private String profile;
	
	public UserDTO() {
		
	}

	public UserDTO(Long id, String name, String email, String password, String profile) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.profile = profile;
	}
	
	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		password = entity.getPassword();
		profile = entity.getProfile();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
