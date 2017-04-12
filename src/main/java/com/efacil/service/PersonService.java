package com.efacil.service;

import java.util.List;

import com.efacil.domain.Person;
import com.efacil.service.data.PersonData;
import com.efacil.web.data.PersonRequest;

public interface PersonService {

	List<Person> list(String credentials);

	PersonData read(String credentials, Long id);

	void destroy(String credentials, Long id);

	PersonData create(String credentials, PersonRequest person);

	void update(String credentials, PersonRequest person, Long id);
}
