package com.blurdel.demo;

import com.blurdel.demo.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BasicRestControllerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objMapper;

//	@BeforeEach
//	void setup(WebApplicationContext wac) {
//		this.mockMvc = MockMvcBuilders.standaloneSetup(new PersonController()).build();
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//	}

	@Test
	void contextLoads() {
	}

	@Test
	void alwaysTrue() {
        assertTrue(true, "Always true");
	}

	@Test
	@Order(1)
	void testRestPost() throws Exception {
		// Add a known Entity to get/delete later
		mockMvc.perform(post("/person")
						.content(objMapper. writeValueAsString(new Person("Zoey!", 15)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	void testRestGetAll() throws Exception {
		mockMvc.perform(get("/person"))
//				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@Order(3)
	void testRestGetAllFailure() throws Exception {
		mockMvc.perform(get("/person/"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@Order(4)
	void testRestGetOne() throws Exception {
		mockMvc.perform(get("/person/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.name").value("Zoey!"))
				.andExpect(jsonPath("$.age").value("15"));
	}

	@Test
	void testRestGetOneFailure() throws Exception {
		mockMvc.perform(get("/person/0"))
				.andExpect(status().isNotFound());
	}

	@Test
	void testRestDeleteOne() throws Exception {
		mockMvc.perform(delete("/person/1"))
				.andExpect(status().isOk());
	}

	@Test
	void testRestDeleteOneFailure() throws Exception {
		mockMvc.perform(delete("/person/0"))
				.andExpect(status().isNotFound());
	}

}
