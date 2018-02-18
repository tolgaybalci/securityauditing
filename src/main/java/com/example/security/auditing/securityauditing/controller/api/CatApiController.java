package com.example.security.auditing.securityauditing.controller.api;

import com.example.security.auditing.securityauditing.domain.Cat;
import com.example.security.auditing.securityauditing.repository.CatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created on February, 2018
 *
 * @author cylon
 */
@Slf4j
@RestController
@RequestMapping("/api/cats")
public class CatApiController {

	@Autowired
	private CatRepository catRepository;

	@GetMapping("")
	public ResponseEntity<Iterable<Cat>> getCatsList() {
		return ResponseEntity.ok(catRepository.findAll());
	}

	@PostMapping("")
	public ResponseEntity<Cat> createNewCat(@Validated @RequestBody Cat cat) {
		catRepository.save(cat);
		return ResponseEntity.ok(cat);
	}

	@PutMapping("")
	public ResponseEntity<Cat> updateCat(@Validated @RequestBody Cat cat) {
		catRepository.save(cat);
		return ResponseEntity.ok(cat);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cat> showCat(@PathVariable Long id) {
		Optional<Cat> catOptional = catRepository.findById(id);

		if(!catOptional.isPresent()){
			log.warn("Cat with id: {} is not present");
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.ok(catOptional.get());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCat(@PathVariable Long id){

		catRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
