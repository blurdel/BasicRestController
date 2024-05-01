package com.blurdel.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.blurdel.demo.model.Person;

public interface MongoPersonRepo extends CrudRepository<Person, String> {

//	Person findById(String id);

	Person findByName(String name);
	
}
