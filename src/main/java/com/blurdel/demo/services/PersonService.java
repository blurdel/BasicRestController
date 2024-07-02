package com.blurdel.demo.services;

import java.util.List;
import java.util.Optional;

import com.blurdel.demo.model.Person;

public interface PersonService {
	
	List<Person> getAll();

	Optional<Person> findById(Long id);

	Optional<Person> add(Person person);

	Optional<Person> update(Person person);

	Optional<Person> delete(Long id);

	Long deleteAll();

	Person findByName(String name);

}
