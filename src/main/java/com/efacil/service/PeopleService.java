package com.efacil.service;

import java.util.List;

import com.efacil.domain.Person;

public interface PeopleService {

    List<Person> getAll();

    Person getOne(Long id);

    void destroy(Long id);

    Person create(Person person);

    void update(Person person, Long id);

    void destroyAll();

}
