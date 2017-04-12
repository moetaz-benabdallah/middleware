package com.efacil.middleware.web.controller.crm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.efacil.service.PersonService;
import com.efacil.web.controller.crm.PersonCrmController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PersonCrmControllerTest {

	@InjectMocks
	PersonCrmController controller;

	@Mock
	PersonService mockPersonService;

	MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetOne() throws Exception {

		this.mvc.perform(MockMvcRequestBuilders.get("/crm/people/393")
				.header("Authorization", "Basic QWRtaW4xOnByb2pldGFzIzEyMw==").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}
	
	@Test
	public void testDeleteOne() throws Exception {

		this.mvc.perform(MockMvcRequestBuilders.delete("/crm/people/393")
				.header("Authorization", "Basic QWRtaW4xOnByb2pldGFzIzEyMw==").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}
}
