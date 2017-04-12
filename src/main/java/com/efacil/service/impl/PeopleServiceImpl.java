/*
 * @(#)PeopleService.java        1.0 2017/03/04
 *
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 */

package com.efacil.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efacil.domain.Person;
import com.efacil.repository.PeopleRepository;
import com.efacil.service.PeopleService;
import com.efacil.service.PersonService;
import com.efacil.service.converter.PersonConverter;
import com.efacil.service.converter.PersonDataConverter;
import com.efacil.service.converter.PersonRequestConverter;
import com.efacil.service.data.PersonData;
import com.efacil.web.data.PersonRequest;

@Service
@Transactional(readOnly = true)
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	private PeopleRepository peopleRepository;

	@Autowired
	private PersonService personService;

	@Override
	public List<PersonData> getAll() {
		return StreamSupport.stream(peopleRepository.findAll().spliterator(), false)
				.map(person -> new PersonConverter().convert(person)).collect(Collectors.toList());
	}

	@Override
	public PersonData getOne(Long id) {
		Person person = peopleRepository.findOne(id);
		if (person == null) {
			throw new EntityNotFoundException();
		}
		return new PersonConverter().convert(person);
	}

	@Override
	@Transactional
	public void destroy(Long id) {
		peopleRepository.delete(id);
		personService.destroy("Basic QWRtaW4xOnByb2pldGFzIzEyMw==", id);
	}

	@Override
	@Transactional
	public PersonData create(PersonRequest request) {
		// TODO personrequest to person
		return new PersonConverter().convert(peopleRepository.save(new PersonRequestConverter().convert(request)));
	}

	@Override
	@Transactional
	public void update(PersonRequest request, Long id) {
		// TODO personrequest to person
		Person person = new PersonDataConverter().convert(getOne(id));
		person.update(request.getName(), request.getBirthDate(), request.getActivated());
		peopleRepository.save(person);
	}

	@Override
	@Transactional
	public void destroyAll() {
		peopleRepository.deleteAll();
	}

}
