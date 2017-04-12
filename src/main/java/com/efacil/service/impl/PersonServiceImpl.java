package com.efacil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efacil.domain.Person;
import com.efacil.service.PeopleService;
import com.efacil.service.PersonService;
import com.efacil.service.converter.PersonConverter;
import com.efacil.service.converter.PersonRequestConverter;
import com.efacil.service.data.PersonData;
import com.efacil.service.request.PersonApi;
import com.efacil.web.data.PersonRequest;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

/**
 * PersonEndpointService Class Endpoints API CRM CRUD. Allows the
 * synchronization with Righnow CRM
 *
 * @author Moatez Ben Abdallah
 * @version 1.0 04 Mar 2017
 **/
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PeopleService peopleService;
	private PersonApi api;
	
	public PersonServiceImpl() {
		api = Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder()).target(PersonApi.class,
				"https://opn-projetas-2.rightnowdemo.com");
	}

	@Override
	public List<Person> list(String credentials) {

		return null;

	}

	@Override
	public PersonData read(String credentials, Long id) {
		return api.getOne(credentials, id);
	}

	@Override
	public void destroy(String credentials, Long id) {
		api.delete(credentials, id);
	}

	@Override
	public PersonData create(String credentials, PersonRequest person) {
		
		PersonRequest p = api.create(credentials, person);
		peopleService.create(p);
		return new PersonConverter().convert(new PersonRequestConverter().convert(p));

	}

	@Override
	public void update(String credentials, PersonRequest person, Long id) {
		peopleService.update(person, id);
		api.update(credentials, person, id);
	}

}
