package com.blurdel.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.blurdel.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.blurdel.demo.services.PersonService;

import java.util.List;

@Profile("mongo")
@SpringBootTest
//@DataMongoTest
//@ContextConfiguration(classes = PersonService.class)
public class BasicRestControllerMongoTests {

	@Autowired
	private PersonService personService;
	
	
	@Test
	void contextLoads() {
		assertNotNull(personService);
	}

	@Test
	void testListNotEmpty() {
		List<Person> list = personService.getAll();
		assertTrue(list.size() > 0, "List should not be empty");
	}

	@Test
	void testFindByName() {
		Person fetched = personService.findByName("Zoey!");
		assertEquals("Zoey!", fetched.getName(), "Names should match");
		assertEquals(15, fetched.getAge(), "Ages should match");
	}

	@Test
	void testFindById() {
		Person fetched = personService.findById("6538640cbf648a3624979eee");
		assertEquals("Zoey!", fetched.getName(), "Names should match");
		assertEquals(15, fetched.getAge(), "Ages should match");
	}
	
}
