package com.blurdel.demo.services;

import java.util.List;
import java.util.Optional;

import com.blurdel.demo.model.Person;

public interface PersonService {
	
	List<Person> getAll();
	
	Optional<Person> getById(Long id);
			
	Person add(Person person);
	
	Person update(Person person);
	
	void delete(Long id);	

}
