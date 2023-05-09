package com.blurdel.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blurdel.demo.model.Person;
import com.blurdel.demo.repositories.PersonRepo;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepo personRepo;
	
	
	@Override
	public Optional<Person> getById(Long id) {
		return personRepo.findById(id);
	}

	@Override
	public List<Person> getAll() {
		return personRepo.findAll();
	}

	@Override
	public Person add(Person person) {
		return personRepo.save(person);
	}

	@Override
	public void delete(Long id) {
		personRepo.deleteById(id);
		
	}

}
