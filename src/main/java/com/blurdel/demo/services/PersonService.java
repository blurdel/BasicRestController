package com.blurdel.demo.services;

import java.util.List;
import java.util.Optional;

import com.blurdel.demo.model.Person;

public interface PersonService {
	
	Optional<Person> getById(Long id);
	
	List<Person> getAll();
	
	Person add(Person person);
	
	void delete(Long id);	

}
