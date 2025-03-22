package com.blurdel.demo.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


	public PersonController(PersonService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		LOG.info("PersonController returning list");
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPerson(@PathVariable final Long id) {
		LOG.info("PersonController GET called for id: {}", id);
		Optional<Person> person = service.findById(id);
		if (person.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		LOG.debug("PersonController GET Response: {}", person.get());
		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addNewPerson(@RequestBody final Person payload) {
		LOG.info("PersonController POST called with payload: {}", payload.toString());
		Optional<Person> saved = service.add(payload);
		if (saved.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.debug("PersonController POST Response: {}", saved.get());
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePerson(@PathVariable final Long id, @RequestBody final Person payload) {
		LOG.info("PersonController PUT called for id {} with payload {}", id, payload.toString());
		if (!id.equals(payload.getId())) {
			throw new IllegalArgumentException("PUT Request pathParam.id should match person.id");
		}

		Optional<Person> updated = service.update(payload);
		if (updated.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		LOG.debug("PersonController PUT Response: {}", updated.get());
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable final Long id) {
		LOG.info("PersonController DELETE called for id {}", id);
		Optional<Person> deleted = service.delete(id);
		if (deleted.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		LOG.debug("PersonController DELETE Response: {}", deleted.get());
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
}
