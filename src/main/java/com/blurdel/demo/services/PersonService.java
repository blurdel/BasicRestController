package com.blurdel.demo.services;

import java.util.List;

import com.blurdel.demo.model.Person;

public interface PersonService {
	
	List<Person> getAll();

	Person findById(String id);
			
	Person add(Person person);
	
	Person update(Person person);
	
	Person delete(String id);

	Long deleteAll();
	
	Person findByName(String name);

}
