package com.efacil.middleware.web.controller.crm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.efacil.service.PersonService;
import com.efacil.service.converter.PersonConverter;
import com.efacil.service.converter.PersonRequestConverter;
import com.efacil.service.data.PersonData;
import com.efacil.web.controller.crm.PersonCrmController;
import com.efacil.web.data.PersonRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@JsonTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration
public class PersonCrmControllerTest {

	@InjectMocks
	PersonCrmController controller;

	@Mock
	PersonService mockPersonService;

	MockMvc mvc;

	@Autowired
	private JacksonTester<PersonData> json;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void shouldGetOne() throws Exception {

		ResultActions response = this.mvc.perform(MockMvcRequestBuilders.get("/crm/people/394")
				.header("Authorization", "Basic QWRtaW4xOnByb2pldGFzIzEyMw==").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());

		String content = response.andReturn().getResponse().getContentAsString();

		assertThat(this.json.parse(content)).isEqualTo(PersonData.builder().id(394L).name("moatez ben abdallah")
				.activated(true).birthDate(new Date()).build());

	}

	@Test
	public void shouldCreate() throws Exception {
		PersonRequest person = Mockito.mock(PersonRequest.class);
		when(person.getName()).thenReturn("xxx");

		assertThat(this.json.parseObject("\"name\":\"xxx\"}").getName()).isEqualTo("xxx");

		ResultActions response = this.mvc.perform(MockMvcRequestBuilders.post("/crm/people", person)
				.header("Authorization", "Basic QWRtaW4xOnByb2pldGFzIzEyMw==").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());

		String content = response.andReturn().getResponse().getContentAsString();

		assertThat(this.json.write(new PersonConverter().convert(new PersonRequestConverter().convert(person))))
				.isEqualToJson(content);
	}

	@Test
	public void shouldUpdate() throws Exception {
		PersonRequest person = Mockito.mock(PersonRequest.class);
		when(person.getName()).thenReturn("xxx");

		assertThat(this.json.parseObject("{\"name\":\"xxx\"}").getName()).isEqualTo("xxx");

		this.mvc.perform(MockMvcRequestBuilders.put("/crm/people/394", person)
				.header("Authorization", "Basic QWRtaW4xOnByb2pldGFzIzEyMw==").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void shouldDeleteOne() throws Exception {

		this.mvc.perform(MockMvcRequestBuilders.delete("/crm/people/393")
				.header("Authorization", "Basic QWRtaW4xOnByb2pldGFzIzEyMw==").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
}
