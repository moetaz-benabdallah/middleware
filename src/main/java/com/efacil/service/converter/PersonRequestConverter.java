package com.efacil.service.converter;

import org.springframework.core.convert.converter.Converter;

import com.efacil.domain.Person;
import com.efacil.web.data.PersonRequest;

public class PersonRequestConverter implements Converter<PersonRequest, Person>{

	@Override
	public Person convert(PersonRequest personRequest) {
		return Person.builder()
				.id(personRequest.getId())
				.name(personRequest.getName())
				.birthDate(personRequest.getBirthDate())
				.activated(personRequest.getActivated())
				.build();
	}

}
