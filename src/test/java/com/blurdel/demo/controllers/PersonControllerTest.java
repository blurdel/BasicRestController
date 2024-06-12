package com.blurdel.demo.controllers;

import com.blurdel.demo.model.Person;
import com.blurdel.demo.services.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objMapper;

	@Autowired
	private PersonService service;

	private static final Person SAMPLE = new Person(null, "Zoey!", 15);

//	@BeforeEach
//	void setup(WebApplicationContext wac) {
//		this.mockMvc = MockMvcBuilders.standaloneSetup(new PersonController()).build();
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//	}

	@AfterEach
	void cleanup() throws Exception {
		for (Person p : service.getAll()) {
			service.delete(p.getId());
		}
	}

	@Test
	void contextLoads() {
	}

	@Test
	void alwaysTrue() {
        assertTrue(true, "Always true");
	}

	@Test
	void testRestPost() throws Exception {
		mockMvc.perform(post("/person")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(objMapper.writeValueAsString(SAMPLE))
				)
				.andExpect(status().isCreated())
//				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value(SAMPLE.getName()))
				.andExpect(jsonPath("$.age").value(SAMPLE.getAge()));
	}

	@Test
	void testRestGetAll() throws Exception {
		// Insert an entity to GET
		service.add(SAMPLE);

		mockMvc.perform(get("/person")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name").value(SAMPLE.getName()))
				.andExpect(jsonPath("$[0].age").value(SAMPLE.getAge()));
	}

	@Test
	void testRestGetAllFailureExtraSlash() throws Exception {
		mockMvc.perform(get("/person/"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void testRestGetOne() throws Exception {
		// Insert an entity to GET
		Optional<Person> added = service.add(SAMPLE);

		mockMvc.perform(get("/person/{id}", added.get().getId())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(added.get().getId()))
				.andExpect(jsonPath("$.name").value(added.get().getName()))
				.andExpect(jsonPath("$.age").value(added.get().getAge()));
	}

	@Test
	void testRestGetOneFailure() throws Exception {
		mockMvc.perform(get("/person/{id}", 0L))
				.andExpect(status().isNotFound());
	}

//	@Test
//	void test() throws Exception {
//		mockMvc.perform(get("/person/{id}", 1L)
//				.param("iscold", "yes") // just testing REST doc
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				)
//				.andExpect(status().isOk());
//	}

	@Test
	void testRestUpdateOne() throws Exception {
		// Insert an entity to UPDATE
		Optional<Person> added = service.add(new Person(null, "Fred", 42));

		// Update the values
		Person updated = new Person(added.get().getId(), "Zoey!", 15);

//		ResultActions resultActions = mockMvc.perform(put("/person/{id}", updated.getId())
		mockMvc.perform(put("/person/{id}", updated.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(objMapper.writeValueAsString(updated))
				)
//				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(updated.getId()))
				.andExpect(jsonPath("$.name").value(updated.getName()))
				.andExpect(jsonPath("$.age").value(updated.getAge()));

//		MvcResult result = resultActions.andReturn();
//		String body = result.getResponse().getContentAsString();
//		Person response = objMapper.readValue(body, Person.class);
	}

	@Test
	void testRestDeleteOne() throws Exception {
		// Insert an entity to DELETE
		Optional<Person> added = service.add(SAMPLE);

		mockMvc.perform(delete("/person/{id}", added.get().getId()))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void testRestDeleteOneFailure() throws Exception {
		mockMvc.perform(delete("/person/{id}", 0L))
				.andExpect(status().isNotFound());
	}

}
