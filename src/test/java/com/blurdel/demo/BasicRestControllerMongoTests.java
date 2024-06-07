package com.blurdel.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.blurdel.demo.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blurdel.demo.services.PersonService;

import java.util.List;

@SpringBootTest
//@DataMongoTest
//@ContextConfiguration(classes = PersonService.class)
public class BasicRestControllerMongoTests {

	@Autowired
	private PersonService personService;

	@BeforeEach
	void init() {
		Person added = personService.add(new Person(null, "Person", 42));
		assertEquals("Person", added.getName(), "Names should match");
		assertEquals(42, added.getAge(), "Ages should match");
	}

	@AfterEach
	void cleanup() {
		for (Person p : personService.getAll()) {
			if (!"Zoey!".equals(p.getName())) {
				System.out.println("clean->" + p.toString());
				personService.delete(p.getId());
			}
		}
	}

	@Test
	void contextLoads() {
		assertNotNull(personService);
	}

	@Test
	void testFindZoey() {
//		Person added = personService.add(new Person(null, "Zoey!", 15));
		Person fetched = personService.findByName("Zoey!");
		assertEquals("Zoey!", fetched.getName());
		assertEquals(15, fetched.getAge());
	}

	@Test
	void testFindAll() {
		List<Person> list = personService.getAll();
		assertTrue(list.size() > 0, "List should not be empty");
	}

	@Test
	void testAddPerson() {
		Person newPerson = new Person(null, "Tana", 11);
		Person saved = personService.add(newPerson);
		assertEquals(newPerson.getName(), saved.getName(), "Names should match");
		assertEquals(newPerson.getAge(), saved.getAge(), "Ages should match");
	}

	@Test
	void testUpdatePerson() {
		Person fetched = personService.findByName("Person");
		fetched.setName("Fred");
		fetched.setAge(99);

		Person updated = personService.update(fetched);
		assertEquals(fetched.getName(), updated.getName(), "Names should match");
		assertEquals(fetched.getAge(), updated.getAge(), "Ages should match");
	}

	@Test
	void testDeletePerson() {
		Person fetched = personService.findByName("Person");
		Person deleted = personService.delete(fetched.getId());
		assertEquals(deleted.getName(), fetched.getName(), "Names should match");
		assertEquals(deleted.getAge(), fetched.getAge(), "Ages should match");
	}

//	@Test
//	void testFindById() {
//		Person fetched = personService.findById("6642cffbd43f8a645c7cf2fb");
//		assertEquals("Zoey!", fetched.getName(), "Names should match");
//		assertEquals(15, fetched.getAge(), "Ages should match");
//	}

}
