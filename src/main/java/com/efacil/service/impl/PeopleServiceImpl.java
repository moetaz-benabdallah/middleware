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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efacil.domain.Person;
import com.efacil.repository.PeopleRepository;
import com.efacil.service.PeopleService;

/**
 * PersonService Class, create CRUD endpoints.
 * Allows the synchronization with Righnow CRM
 *
 * @author Moatez Ben Abdallah
 * @version 1.0 04 Mar 2017
 **/
@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	PeopleRepository peopleRepository; 
	
	@Autowired
	PersonServiceImpl personService;
	
    @Override
    public List<Person> getAll() {
        return peopleRepository.findAll();
    }

    @Override
    public Person getOne(Long id) {
        return peopleRepository.getOne(id);
    }

    @Override
    public void destroy(Long id) {
    	personService.destroy("Basic QWRtaW4xOnByb2pldGFzIzEyMw==", id);
        peopleRepository.delete(id);
    }

	@Override
    public Person create(Person person) {
    	return peopleRepository.save(person);
    }

    @Override
    public void update(Person person, Long id) {
        peopleRepository.update(person, id);
    }

    @Override
    public void destroyAll() {
        peopleRepository.deleteAll();
    }

}
