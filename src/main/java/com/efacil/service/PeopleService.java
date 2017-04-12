package com.efacil.service;

import java.util.List;

import com.efacil.service.data.PersonData;
import com.efacil.web.data.PersonRequest;

public interface PeopleService {

    List<PersonData> getAll();

    PersonData getOne(Long id);

    void destroy(Long id);

    PersonData create(PersonRequest person);

    void update(PersonRequest person, Long id);

    void destroyAll();

}
