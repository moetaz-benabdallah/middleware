package com.efacil.web.controller.crm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.efacil.service.PersonService;
import com.efacil.service.data.PersonData;
import com.efacil.web.data.PersonRequest;

@Controller
@RequestMapping(value = "/crm/people")
public class PersonCrmController {

	@Autowired
	private PersonService personService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public PersonData create(@RequestHeader(value = "Authorization", required = true) String credentials,
			@RequestBody @Valid PersonRequest person) {
		return personService.create(credentials, person);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	void update(@RequestHeader(value = "Authorization", required = true) String credentials,
			@RequestBody PersonRequest person, @PathVariable("id") Long id) {
		personService.update(credentials, person, id);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	PersonData getOneById(@RequestHeader(value = "Authorization", required = true) String credentials,
			@PathVariable("id") Long id) {
		return personService.read(credentials, id);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	void delete(@RequestHeader(value = "Authorization", required = true) String credentials,
			@PathVariable("id") Long id) {
		personService.destroy(credentials, id);

	}
}
