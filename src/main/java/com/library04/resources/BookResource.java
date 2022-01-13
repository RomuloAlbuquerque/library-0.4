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

import com.library04.dtos.BookDTO;
import com.library04.entities.Profile;
import com.library04.services.BookService;
import com.library04.services.ProfileService;

@RestController
@RequestMapping(value = "/books")
public class BookResource {

	@Autowired
	private BookService service;

	@Autowired
	private ProfileService profileService;

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody BookDTO dto) {
		List<Profile> profileLoged = profileService.findAll();
		if (!profileLoged.isEmpty()) {
			dto.setBy(profileService.returnIdProfileLoged());
			BookDTO newDto = service.create(dto);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId())
					.toUri();
			return ResponseEntity.created(uri).body(newDto);
		} else {
			return ResponseEntity.badRequest().body("VOCÊ PRECISA FAZER LOGIN");
		}
	}

	@GetMapping(value = "/read")
	public ResponseEntity<Object> read() {
		List<Profile> profileLoged = profileService.findAll();
		if (!profileLoged.isEmpty()) {
			List<BookDTO> dtoList = new ArrayList<>();
			dtoList = service.read();
			return ResponseEntity.ok().body(dtoList);
		} else {
			return ResponseEntity.badRequest().body("VOCÊ PRECISA FAZER LOGIN");
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BookDTO dto) {
		List<Profile> profileLoged = profileService.findAll();
		if (!profileLoged.isEmpty()) {
			dto.setBy(profileService.returnIdProfileLoged());
			BookDTO newDto = service.update(id, dto);
			return ResponseEntity.ok().body(newDto);
		} else {
			return ResponseEntity.badRequest().body("VOCÊ PRECISA FAZER LOGIN");
		}
	}

	@PutMapping(value = "/update/{title}")
	public ResponseEntity<?> updateByTitle(@PathVariable String title, @RequestBody BookDTO dto) {
		List<Profile> profileLoged = profileService.findAll();
		if (!profileLoged.isEmpty()) {
			BookDTO bookDTO = service.findEntityByTitle(title);
			//dto.setBy(profileService.returnIdProfileLoged());

			if (!profileService.returnProfileLoged().equals("admin")) {
				if (bookDTO.getBy().equals(profileService.returnIdProfileLoged())) {
					BookDTO newDto = service.updateByTitle(title, dto);
					return ResponseEntity.ok().body(newDto);
				} else {
					return ResponseEntity.badRequest()
							.body("Você não é ADMINISTRADOR. Seu perfil é de CLIENTE, você só tem permissão para atualizar livros que você mesmo cadastrou!");
				}
			} else {
				BookDTO newDto = service.updateByTitle(title, dto);
				return ResponseEntity.ok().body(newDto);
			}
		} else {
			return ResponseEntity.badRequest().body("VOCÊ PRECISA FAZER LOGIN");
		}
	}

	@GetMapping(value = "/title/{title}")
	public ResponseEntity<?> findByTitle(@PathVariable String title) {
		List<Profile> profileLoged = profileService.findAll();
		if (!profileLoged.isEmpty()) {
			List<BookDTO> dtoList = new ArrayList<>();
			dtoList = service.findByTitle(title);
			ResponseEntity<List<BookDTO>> json = ResponseEntity.ok().body(dtoList);
			return json;
		} else {
			return ResponseEntity.badRequest().body("VOCÊ PRECISA FAZER LOGIN");
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		List<Profile> profileLoged = profileService.findAll();
		if (!profileLoged.isEmpty()) {
			BookDTO dto = service.findById(id);
			return ResponseEntity.ok().body(dto);
		} else {
			return ResponseEntity.badRequest().body("VOCÊ PRECISA FAZER LOGIN");
		}
	}

	@DeleteMapping(value = "delete/{title}")
	public ResponseEntity<?> delete(@PathVariable String title) {
		List<Profile> profileLoged = profileService.findAll();
		if (!profileLoged.isEmpty()) {
			BookDTO dto = service.findEntityByTitle(title);
			if (!profileService.returnProfileLoged().equals("admin")) {
				if (dto.getBy().equals(profileService.returnIdProfileLoged())) {
					service.delete(title);
					return ResponseEntity.noContent().build();
				} else {
					return ResponseEntity.badRequest().body("Você não é ADMINISTRADOR. Seu perfil é de CLIENTE, você só tem permissão para deletar livros que você mesmo cadastrou!");
				}
			} else {
				service.delete(title);
				return ResponseEntity.noContent().build();
			}
		} else {
			return ResponseEntity.badRequest().body("VOCÊ PRECISA FAZER LOGIN");
		}
	}
}
