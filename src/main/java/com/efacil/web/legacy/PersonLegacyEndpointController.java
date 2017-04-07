package com.efacil.web.legacy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.efacil.domain.Person;
import com.efacil.service.PeopleService;

@Controller
@RequestMapping(value = "/legacy")
@ResponseBody
public class PersonLegacyEndpointController {
	
	@Autowired
	PeopleService peopleService;
	
	@RequestMapping(value = "/people", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	Person create(@RequestBody Person person) {
		return peopleService.create(person);
	}
	
	@RequestMapping(value = "/people", method = RequestMethod.GET, produces = "application/json")
	List<Person> getAll() {
		return peopleService.getAll();
	}
	
	@RequestMapping(value = "/people/{id}", method = RequestMethod.GET, produces = "application/json")
	Person getOneById(@PathVariable("id") Long id) {
		return peopleService.getOne(id);
	}
	
	@RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
	void delete(@PathVariable("id") Long id) {
		peopleService.destroy(id);
	}
	
	@RequestMapping(value = "/people/{id}", method = RequestMethod.PUT, consumes = "application/json")
	void update(@RequestBody Person person, @PathVariable("id") Long id) {
		peopleService.update(person, id);
	}
	
	@RequestMapping(value = "/people", method = RequestMethod.DELETE)
	void deleteAll() {
		peopleService.destroyAll();
	}
	

}
