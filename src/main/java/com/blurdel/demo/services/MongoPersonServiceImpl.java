package com.blurdel.demo.services;

import java.util.ArrayList;
import java.util.List;

import com.blurdel.demo.repositories.MongoPersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.blurdel.demo.model.Person;

@Profile("mongo")
@Service
public class MongoPersonServiceImpl implements PersonService {

	@Autowired
	private MongoPersonRepo repo;

	
	@Override
	public List<Person> getAll() {
		ArrayList<Person > list = new ArrayList<>();
		repo.findAll().forEach(list::add);
		return list;
	}

	@Override
	public Person findById(String id) {
		return repo.findById(id);
	}

	@Override
	public Person add(Person person) {
		return repo.save(person);
	}

	@Override
	public Person update(Person person) {
		return repo.save(person);
	}

	@Override
	public Person delete(String id) {
		Person fetched = findById(id);
		if (fetched != null) {
			repo.delete(fetched);
		}
		return fetched;
	}

	@Override
	public Person findByName(String name) {
		return repo.findByName(name);
	}

}
