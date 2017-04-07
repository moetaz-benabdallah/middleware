package com.efacil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efacil.domain.Person;
import com.efacil.service.PeopleService;
import com.efacil.service.PersonService;
import com.efacil.service.request.PersonApi;

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
	PeopleService peopleService;
	PersonApi api;
	
	public PersonServiceImpl() {
		api = Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder()).target(PersonApi.class,
				"https://opn-projetas-2.rightnowdemo.com");
	}

	@Override
	public List<Person> list(String credentials) {

		return null;

	}

	@Override
	public Person read(String credentials, Long id) {
		return api.getOne(credentials, id);
	}

	@Override
	public void destroy(String credentials, Long id) {
		api.delete(credentials, id);
	}

	@Override
	public Person create(String credentials, Person person) {
		Person p = api.create(credentials, person);
		peopleService.create(p);
		return p;
	}

	@Override
	public void update(String credentials, Person person, Long id) {
		peopleService.update(person, id);
		api.update(credentials, person, id);
	}

}
