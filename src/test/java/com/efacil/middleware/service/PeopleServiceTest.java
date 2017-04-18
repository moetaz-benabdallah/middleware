package com.efacil.middleware.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.efacil.service.PeopleService;
import com.efacil.service.data.PersonData;
import com.efacil.web.data.PersonRequest;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleServiceTest {

	@MockBean
	private PeopleService peopleService;
	
	@Test
	public void shouldCreate(){
		PersonRequest personR = Mockito.mock(PersonRequest.class);
		when(personR.getName()).thenReturn("xxx");
		
		PersonData personD = Mockito.mock(PersonData.class);
		when(personD.getName()).thenReturn("xxx");

		given(this.peopleService.create(personR)).willReturn(personD);
		assertThat(personR.getName()).isEqualTo(personD.getName());

	}

	@Test
	public void shouldGetAll() {

		PersonData obj = PersonData.builder().id(1L).name("xxx").build();
		
		List<PersonData> people = new ArrayList<PersonData>();
		people.add(obj);
		
		given(this.peopleService.getAll()).willReturn(people);
//
//		when(peopleService.getAll()).thenReturn(Arrays.asList(obj));
//		
//		List<PersonData> actual = peopleService.getAll();
//		assertThat(actual).hasSize(1);      
	}
	
	@Test
	public void shouldDeleteOne(){
		
		PersonData obj = PersonData.builder().id(1L).name("xxx").build();

		when(peopleService.getOne(1L)).thenReturn(obj);
		peopleService.destroy(1L);
		verify(peopleService, times(1)).destroy(1L);
	}
	
	@Test
	public void shouldUpdate() {
		PersonData existing = PersonData.builder().id(1L).name("xxx").build();
		
		PersonRequest personR = Mockito.mock(PersonRequest.class);
		when(personR.getName()).thenReturn("xxx");
		
		when(peopleService.getOne(1L)).thenReturn(existing);
		when(peopleService.create(personR)).thenReturn(existing);
		
		PersonRequest personR2 = Mockito.mock(PersonRequest.class);
		when(personR2.getName()).thenReturn("yyy");
		
		peopleService.update(personR2, 1L);
		verify(peopleService, times(1)).create(personR);
		
		assertThat(existing.getName()).isEqualTo("xxx");
	}

}
