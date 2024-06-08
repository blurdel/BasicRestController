package com.blurdel.demo.controllers;

import java.util.Objects;

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

	private final PersonService service;


    public PersonController(PersonService service) {
        this.service = service;
    }


    @GetMapping
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable final String id) {
		Objects.requireNonNull(id, "id can not be null");

		Person person = service.findById(id);
		if (person == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(person, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addNewPerson(@RequestBody final Person person) {
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
	public ResponseEntity<?> updatePerson(@PathVariable final String id, @RequestBody final Person person) {
		if (person == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		Person updated = service.update(new Person(String.valueOf(id), person.getName(), person.getAge()));
		if (updated == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOne(@PathVariable final String id) {
		Objects.requireNonNull(id, "id can not be null");

		Person deleted = service.delete(id);
		if (deleted == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
}
