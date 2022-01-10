package com.library04.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library04.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "select user from User user where user.email like %?1%")
	List<User> findByEmail(String email);
	
	@Query(value = "select user from User user where user.email like %?1%")
	User findEntityByEmail(String email);
	
	Optional<User> findOptionalByEmail(String email);
}
