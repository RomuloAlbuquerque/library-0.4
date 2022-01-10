package com.library04.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library04.dtos.UserDTO;
import com.library04.entities.User;
import com.library04.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Transactional
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Transactional
	public List<UserDTO> read() {
		List<User> entityList = new ArrayList<>();
		entityList = repository.findAll();
		List<UserDTO> dtoList = new ArrayList<>();
		for (User entity : entityList) {
			UserDTO dto = new UserDTO(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		User entity = repository.getById(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO updateByEmail(String email, UserDTO dto) {
		User entity = repository.findEntityByEmail(email);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Transactional
	public List<UserDTO> findByEmail(String email) {
		List<User> entityList = new ArrayList<>();
		entityList = repository.findByEmail(email);
		List<UserDTO> dtoList = new ArrayList<>();
		for (User entity : entityList) {
			UserDTO dto = new UserDTO(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Transactional
	public UserDTO findEntityByEmail(String email) {
		User entity = repository.findEntityByEmail(email);
		UserDTO dto = new UserDTO(entity);
		return dto;
	}

	@Transactional
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.get();
		return new UserDTO(entity);
	}

	public void delete(String email) {
		User entity = repository.findEntityByEmail(email);
		repository.deleteById(entity.getId());
	}

	@Transactional
	public Boolean login(String email, String password) {
		Boolean auth = false;
		Optional<User> user = repository.findOptionalByEmail(email);
		if (!user.isPresent()) {
			auth = false;
		}else
		if (!user.get().getPassword().equals(password)) {
			auth = false;
		}else
		if (user.get().getPassword().equals(password)) {
			auth = true;
		}
		return auth;
	}

	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setProfile(dto.getProfile());
	}

}
