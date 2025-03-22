package com.blurdel.demo.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blurdel.demo.model.Person;
import com.blurdel.demo.repositories.PersonRepo;

@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepo personRepo;


	public PersonServiceImpl(PersonRepo personRepo) {
		this.personRepo = personRepo;
	}

	@Override
	public List<Person> getAll() {
		return personRepo.findAll();
	}

	@Override
	public Optional<Person> findById(final Long id) {
		Objects.requireNonNull(id, "id can not be null");
		return personRepo.findById(id);
	}

	@Override
	public Optional<Person> add(final Person person) {
		Objects.requireNonNull(person, "person can not be null");
		return Optional.of(personRepo.save(person));
	}

	@Override
	public Optional<Person> update(final Person person) {
		Objects.requireNonNull(person, "person can not be null");
		Optional<Person> opt = personRepo.findById(person.getId());
		if (opt.isEmpty()) {
			return Optional.empty();
		}
		Person updated = opt.get(); // retain original id value
		updated.setName(person.getName());
		updated.setAge(person.getAge());
		return Optional.of(personRepo.save(updated));
	}

	@Override
	public Optional<Person> delete(final Long id) {
		Objects.requireNonNull(id, "id can not be null");
		Optional<Person> person = findById(id);
        person.ifPresent(p -> personRepo.deleteById(p.getId()));
		return person;
	}

	@Override
	public Long deleteAll() {
		Long count = 0L;
		for (Person p : getAll()) {
			personRepo.delete(p);
			++count;
		}
		return count;
	}

	@Override
	public Person findByName(String name) {
		Objects.requireNonNull(name, "name can not be null");
		return personRepo.findByName(name);
	}

}
