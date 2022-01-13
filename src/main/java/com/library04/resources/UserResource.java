package com.library04.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.library04.dtos.UserDTO;
import com.library04.services.ProfileService;
import com.library04.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@Autowired
	private ProfileService profileService;

	@PostMapping("/create")
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
		UserDTO newDto = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PostMapping("/create-client")
	public ResponseEntity<UserDTO> createClient(@RequestBody UserDTO dto) {
		UserDTO newDto = service.createClient(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@GetMapping(value = "/read")
	public Object read() {
		List<UserDTO> dtoList = new ArrayList<>();
		dtoList = service.read();
		Object json = ResponseEntity.ok().body(dtoList);
		return json;
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto) {
		UserDTO newDto = service.update(id, dto);
		return ResponseEntity.ok().body(newDto);
	}

	@PutMapping(value = "/update/{email}")
	public ResponseEntity<UserDTO> updateByEmail(@PathVariable String email, @RequestBody UserDTO dto) {
		UserDTO newDto = service.updateByEmail(email, dto);
		return ResponseEntity.ok().body(newDto);
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<List<UserDTO>> findByEmail(@PathVariable String email) {
		List<UserDTO> dtoList = new ArrayList<>();
		dtoList = service.findByEmail(email);
		ResponseEntity<List<UserDTO>> json = ResponseEntity.ok().body(dtoList);
		return json;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		UserDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "delete/{email}")
	public ResponseEntity<Void> delete(@PathVariable String email) {
		service.delete(email);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody UserDTO dto) {
		String auth = service.login(dto.getEmail(), dto.getPassword());
		UserDTO dtoAuth = new UserDTO();
		if (auth == "OK") {
			dtoAuth = service.findEntityByEmail(dto.getEmail());
			profileService.profileLog(dtoAuth.getId() ,dtoAuth.getEmail(), dtoAuth.getProfile());
			return ResponseEntity.ok(dtoAuth.getName());
		}
		else {
			return ResponseEntity.badRequest().body(auth);
		}
	}
	
	@DeleteMapping(value = "logoff")
	public ResponseEntity<?> logoff(){
		profileService.logoff();
		return ResponseEntity.ok().body("Você se desconectou!");
	}

}
