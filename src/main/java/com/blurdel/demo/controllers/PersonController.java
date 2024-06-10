package com.blurdel.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);


	@Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }


    @GetMapping
	public ResponseEntity<?> getAll() {
		LOG.info("PersonController GET");
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable final String id) {
		LOG.info("PersonController GET with id {}", id);
		Person person = service.findById(id);
		if (person == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(person, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addNewPerson(@RequestBody final Person person) {
		LOG.info("PersonController POST with person {}", person);
		Person saved = service.add(person);
		if (saved == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePerson(@PathVariable final String id, @RequestBody final Person person) {
		LOG.info("PersonController PUT with id {}, person {}", id, person);
		Person updated = service.update(new Person(String.valueOf(id), person.getName(), person.getAge()));
		if (updated == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable final String id) {
		LOG.info("PersonController DELETE with id {}", id);
		Person deleted = service.delete(id);
		if (deleted == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
}
