package com.efacil.repository;

import com.efacil.domain.Person;

import java.util.List;

public interface PeopleRepository {

	void checkExistingFile();
	
    List<Person> findAll();

    Person getOne(Long id);

    Person save(Person entity);

    void delete(Long id);
    
    void update(Person entity, Long id);
    
    void deleteAll();
    
}
