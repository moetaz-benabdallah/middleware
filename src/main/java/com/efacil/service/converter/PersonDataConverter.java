package com.efacil.service.converter;

import org.springframework.core.convert.converter.Converter;

import com.efacil.domain.Person;
import com.efacil.service.data.PersonData;

public class PersonDataConverter implements Converter<PersonData, Person>{

	@Override
	public Person convert(PersonData personData) {
		return Person.builder()
				.id(personData.getId())
				.name(personData.getName())
				.birthDate(personData.getBirthDate())
				.activated(personData.getActivated())
				.build();
	}

}
