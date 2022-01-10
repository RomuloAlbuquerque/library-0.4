package com.library04.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library04.entities.Profile;
import com.library04.repositories.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository repository;
	
	private Profile profileLoged = new Profile();
	
	public void profileLog(Long id, String email, String profile) {
		profileLoged.setIdLog(id);
		profileLoged.setEmailLog(email);
		profileLoged.setProfileLog(profile);
		repository.save(profileLoged);
	}
	
	public Long returnIdProfileLoged() {
		return profileLoged.getIdLog();
	}
	
	public String returnProfileLoged() {
		return profileLoged.getProfileLog();
	}
	
	public String returnEmailLoged() {
		return profileLoged.getEmailLog();
	}
	
	public void logoff() {
		repository.deleteAll();
	}
	
	public List<Profile> findAll() {
		List<Profile> entityList = new ArrayList<>();
		entityList = repository.findAll();
		return entityList;
	} 

}
