package com.blurdel.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blurdel.demo.model.Person;
import com.blurdel.demo.services.PersonService;

@RestController
@RequestMapping({"/", "/person"})
public class PersonController {

	@Autowired
	private PersonService service;


	@GetMapping
	public List<Person> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		Optional<Person> opt = service.getById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> addNewPerson(@RequestBody Person person) {
		Person saved = service.add(person);
		if (saved != null) {
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody Person person) {
		person.setId(id);
		Person updated = service.update(person);
		if (updated != null) {
			return new ResponseEntity<>(updated, HttpStatus.CREATED);				
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOne(@PathVariable Long id) {
		service.delete(id);
	}
	
}
