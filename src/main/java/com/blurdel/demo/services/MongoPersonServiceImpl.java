package com.blurdel.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.blurdel.demo.repositories.MongoPersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blurdel.demo.model.Person;

@Service
public class MongoPersonServiceImpl implements PersonService {

	private final MongoPersonRepo repo;

	@Autowired
	public MongoPersonServiceImpl(final MongoPersonRepo pMongoPersonRepo) {
		this.repo = pMongoPersonRepo;
	}

	@Override
	public List<Person> getAll() {
		ArrayList<Person > list = new ArrayList<>();
		repo.findAll().forEach(list::add);
		return list;
	}

	@Override
	public Person findById(final String id) {
		Objects.requireNonNull(id, "id can not be null");
		return repo.findById(id).orElse(null);
	}

	@Override
	public Person add(final Person person) {
		Objects.requireNonNull(person, "person can not be null");
		return repo.save(person);
	}

	@Override
	public Person update(final Person person) {
		Objects.requireNonNull(person, "person can not be null");
		return repo.save(person);
	}

	@Override
	public Person delete(final String id) {
		Objects.requireNonNull(id, "id can not be null");
		Person fetched = findById(id);
		if (fetched != null) {
			repo.delete(fetched);
		}
		return fetched;
	}

	@Override
	public Long deleteAll() {
		Long count = 0L;
		for (Person p : getAll()) {
			repo.delete(p);
			++count;
		}
		return count;
	}

	@Override
	public Person findByName(final String name) {
		Objects.requireNonNull(name, "name can not be null");
		return repo.findByName(name);
	}

}
