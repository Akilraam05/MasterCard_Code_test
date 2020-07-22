package com.mastercard;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc 
public class MastercardApplicationTests {
	private static final String DESTINATION = "destination";
	private static final String ORIGIN = "origin";
	private static final String URL = "/connected";
	@Autowired
	private MockMvc mockMvc;
	

	@Test
	public void testValidInterconnect() throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders.get(URL).param(ORIGIN, "Boston").param(DESTINATION, "Philadelphia").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(content().string("Yes"));
	}
	@Test
	public void testValidDirectConnect() throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders.get(URL).param(ORIGIN, "Trenton").param(DESTINATION, "Albany").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(content().string("Yes"));
	}
	@Test
	public void testValidSameCity() throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders.get(URL).param(ORIGIN, "Trenton").param(DESTINATION, "Trenton").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(content().string("Yes"));
	}
	
	@Test
	public void testInvalidConnect() throws Exception {
		mockMvc
		.perform(MockMvcRequestBuilders.get(URL).param(ORIGIN, "Philadelphia").param(DESTINATION, "Trenton").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(content().string("No"));
	}
}
