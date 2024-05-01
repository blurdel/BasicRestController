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
	public ResponseEntity<?> getOne(@PathVariable String id) {
		Person person = service.findById(id);
		if (person == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(person, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addNewPerson(@RequestBody Person person) {
		if (person == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		Person saved = service.add(person);
		if (saved == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody Person person) {
		if (person == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		person.setId(String.valueOf(id));
		Person updated = service.update(person);
		if (updated == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOne(@PathVariable String id) {
		Person deleted = service.delete(id);
		if (deleted == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
}
