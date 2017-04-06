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
@RequestMapping(value = "/legacy/people")
public class PersonLegacyEndpointController {
	
	@Autowired
	PeopleService peopleService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	Person create(@RequestBody Person person) {
		return peopleService.create(person);
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	List<Person> getPeople() {
		return peopleService.getAll();
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	Person getById(@PathVariable("id") Long id) {
		return peopleService.getOne(id);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	void peopleDestroy(@PathVariable("id") Long id) {
		peopleService.destroy(id);
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseBody
	void peopleUpdate(@RequestBody Person person, @PathVariable("id") Long id) {
		peopleService.update(person, id);
	}
	
	@RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
	@ResponseBody
	void destroyAll() {
		peopleService.destroyAll();
	}
	

}
