package com.efacil.service;

import java.util.List;

import com.efacil.domain.Person;

public interface PersonService {

	List<Person> list(String credentials);

	Person read(String credentials, Long id);

	void destroy(String credentials, Long id);

	Person create(String credentials, Person person);

	void update(String credentials, Person person, Long id);
}
