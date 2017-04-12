package com.efacil.web.controller.legacy;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.efacil.service.PeopleService;
import com.efacil.service.data.PersonData;
import com.efacil.web.data.PersonRequest;

@Controller
@RequestMapping(value = "/legacy/people")
public class PersonLegacyController {
	
	@Autowired
	private PeopleService peopleService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	PersonData create(@RequestBody @Valid PersonRequest person) {
		return peopleService.create(person);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	List<PersonData> getAll() {
		return peopleService.getAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	PersonData getOneById(@PathVariable("id") Long id) {
		return peopleService.getOne(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void delete(@PathVariable("id") Long id) {
		peopleService.destroy(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	void update(@RequestBody PersonRequest person, @PathVariable("id") Long id) {
		peopleService.update(person, id);;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	void deleteAll() {
		peopleService.destroyAll();
	}
	

}
