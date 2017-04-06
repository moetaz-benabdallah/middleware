package com.efacil.repository.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.efacil.domain.Person;
import com.efacil.repository.PeopleRepository;
import com.efacil.repository.exception.EntityNotFoundException;
import com.efacil.repository.exception.RepositoryException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

@Repository
public class PeopleRepositoryImpl implements PeopleRepository {

	@Override
	public void checkExistingFile() {
		try {
			File f = new File(System.getProperty("java.io.tmpdir"),
					"/people.json"); /* Save JSON file under /tmp */
			if (!f.exists()) {
				f.getParentFile().mkdirs();
				f.createNewFile();
				PrintWriter writer = new PrintWriter(System.getProperty("java.io.tmpdir"), "/people.json");
				writer.print("[]");
				writer.close();
			}
		} catch (IOException fe) {
			throw new RepositoryException(String.format("File does not exist.", fe));
		}
	}

	@Override
	public List<Person> findAll() {
		checkExistingFile();
		try {
			File f = new File(System.getProperty("java.io.tmpdir"), "/people.json");
			String content = Files.toString(f, Charsets.UTF_8);

			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(content, new TypeReference<List<Person>>() {
			});

		} catch (Exception e) {
			throw new RepositoryException(String.format("Error fetching list of person."), e);
		}

	}

	@Override
	public Person getOne(Long id) {
		checkExistingFile();
		Person p = null;
		try {
			File f = new File(System.getProperty("java.io.tmpdir"), "/people.json");
			String content = Files.toString(f, Charsets.UTF_8);

			ObjectMapper mapper = new ObjectMapper();
			List<Person> listPerson = mapper.readValue(content, new TypeReference<List<Person>>() {
			});
			for (Person person : listPerson) {
				if (person.getId().equals(id)) {
					p = person;
					break;
				}
			}
			return p;

		} catch (Exception e) {
			throw new RepositoryException(String.format("Error fetching person with id = %l", id), e);
		}
	}

	@Override
	public Person save(Person entity) {
		checkExistingFile();
		try {
			File f = new File(System.getProperty("java.io.tmpdir"), "/people.json");
			String content = Files.toString(f, Charsets.UTF_8);

			ObjectMapper mapper = new ObjectMapper();
			List<Person> listPerson = mapper.readValue(content, new TypeReference<List<Person>>() {
			});
			listPerson.add(entity);

			mapper.writeValue(new File(System.getProperty("java.io.tmpdir"), "/people.json"), listPerson);

			return entity;

		} catch (Exception e) {
			throw new RepositoryException(String.format("Cannot save person."), e);
		}
	}

	@Override
	public void delete(Long id) {
		checkExistingFile();
		try {
			File f = new File(System.getProperty("java.io.tmpdir"), "/people.json");
			String content = Files.toString(f, Charsets.UTF_8);

			ObjectMapper mapper = new ObjectMapper();
			List<Person> listPerson = mapper.readValue(content, new TypeReference<List<Person>>() {
			});

			for (int index = 0; index < listPerson.size(); index++) {
				if (listPerson.get(index).getId().equals(id)) {
					listPerson.remove(index);
					mapper.writeValue(new File(System.getProperty("java.io.tmpdir"), "/people.json"), listPerson);
					break;
				}
			}

		} catch (Exception e) {
			throw new EntityNotFoundException(String.format("Cannot delete person."), e);
		}
	}

	@Override
	public void update(Person entity, Long id) {
		checkExistingFile();
		try {
			File f = new File(System.getProperty("java.io.tmpdir"), "/people.json");
			String content = Files.toString(f, Charsets.UTF_8);

			ObjectMapper mapper = new ObjectMapper();
			List<Person> listPerson = mapper.readValue(content, new TypeReference<List<Person>>() {
			});

			for (int index = 0; index < listPerson.size(); index++) {
				if (listPerson.get(index).getId().equals(id)) {
					listPerson.remove(index);
					listPerson.add(entity);
					mapper.writeValue(new File(System.getProperty("java.io.tmpdir"), "/people.json"), listPerson);
					break;
				}
			}

		} catch (Exception e) {
			throw new RepositoryException(String.format("Error updating person with id = %l", id), e);
		}
	}

	@Override
	public void deleteAll() {
		try {
			PrintWriter writer = new PrintWriter(System.getProperty("java.io.tmpdir") + "/people.json");
			writer.print("[]");
			writer.close();
		} catch (Exception e) {
			throw new RepositoryException(String.format("Error deleting all people"), e);
		}
	}
}
