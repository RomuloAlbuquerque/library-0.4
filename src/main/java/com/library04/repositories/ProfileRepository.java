package com.library04.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library04.entities.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
