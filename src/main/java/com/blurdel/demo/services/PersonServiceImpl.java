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
	public List<Person> getAll() {
		return personRepo.findAll();
	}

	@Override
	public Person getById(Long id) {
		return personRepo.findById(id).orElse(null);
	}

	@Override
	public Person add(Person person) {
		return personRepo.save(person);
	}

	@Override
	public Person update(Person person) {
		Optional<Person> opt = personRepo.findById(person.getId());
		if (!opt.isPresent()) {
			return null;
		}
		Person updated = opt.get(); // retain original id value
		updated.setName(person.getName());
		updated.setAge(person.getAge());
		return personRepo.save(updated);
	}

	@Override
	public Person delete(Long id) {
		Person person = getById(id);
		if (person != null) {
			personRepo.deleteById(person.getId());
		}
		return person;
	}

}
