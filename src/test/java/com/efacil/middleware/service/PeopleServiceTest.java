package com.efacil.middleware.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.efacil.domain.Person;
import com.efacil.service.PeopleService;
import com.efacil.service.converter.PersonConverter;
import com.efacil.service.data.PersonData;
import com.efacil.web.data.PersonRequest;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PeopleServiceTest {

	@Autowired
	private PeopleService peopleService;
	
	@Test
	public void testInit(){
		System.out.println("PeopleServiceTest init");
	}


//	@Test
//	public void testGetAll() {
//
//		List<PersonData> people = peopleService.getAll();
//
//		assertEquals(2, people.size());
//	}
	
//	@Test
//	public void testGetOne() {
//
//		PersonData person = peopleService.getOne(56L);
//		assertEquals("aymen", person.getName());
//	}
	
//	@Test
//	public void testDeleteOne(){
//		int peopleSize = peopleService.getAll().size();
//		peopleService.destroy(391L);
//		int peopleAfterDelete = peopleService.getAll().size();
//		assertEquals(peopleAfterDelete, peopleSize - 1);
//	}

}
