package com.efacil.service.converter;

import org.springframework.core.convert.converter.Converter;

import com.efacil.domain.Person;
import com.efacil.service.data.PersonData;

public class PersonConverter implements Converter<Person, PersonData> {

	@Override
	public PersonData convert(Person person) {
		return PersonData.builder()
				.id(person.getId())
				.name(person.getName())
				.birthDate(person.getBirthDate())
				.activated(person.getActivated())
				.build();
	}

}
