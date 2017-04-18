package com.efacil.middleware.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.efacil.domain.Person;
import com.efacil.repository.PeopleRepository;
import com.efacil.service.converter.PersonConverter;
import com.efacil.service.converter.PersonDataConverter;
import com.efacil.service.data.PersonData;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PeopleRepositoryTest {

	@Autowired
	private PeopleRepository peopleRepository;

	@Test
	public void shouldGetOne() {

		peopleRepository.save(new Person().builder().id(300L).build());

		PersonData person = new PersonConverter().convert(peopleRepository.findOne(300L));
		assertEquals(new Long(300), person.getId());
	}

	@Test
	public void shouldUpdate() {
		peopleRepository.save(new Person().builder().id(400L).name("moatez").build());

		Person person = new PersonDataConverter()
				.convert(new PersonConverter().convert(peopleRepository.findOne(400L)));

		person.update("moatezUpdated", person.getBirthDate(), person.getActivated());
		new PersonConverter().convert(peopleRepository.save(person));

		assertEquals("moatezUpdated", new PersonConverter().convert(peopleRepository.findOne(400L)).getName());

	}

	@Test
	public void shouldGetAll() {

		List<PersonData> people = StreamSupport.stream(peopleRepository.findAll().spliterator(), false)
				.map(person -> new PersonConverter().convert(person)).collect(Collectors.toList());
		
		assertEquals(2, people.size());

	}
	
	@Test
	public void shouldDeleteOne(){
		
		peopleRepository.save(new Person().builder().id(400L).build());
		
		int peopleSize = StreamSupport.stream(peopleRepository.findAll().spliterator(), false)
				.map(person -> new PersonConverter().convert(person)).collect(Collectors.toList()).size();
		
		peopleRepository.delete(400L);
		
		int peopleSizeAfterDelete = StreamSupport.stream(peopleRepository.findAll().spliterator(), false)
				.map(person -> new PersonConverter().convert(person)).collect(Collectors.toList()).size();
		
		assertEquals(peopleSizeAfterDelete+1, peopleSize);
	}

}
