package com.efacil.web.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.efacil.domain.Person;
import com.efacil.service.PersonService;

@Controller
@RequestMapping(value = "/crm/people")
public class PersonCrmEndpointController {

	@Autowired
	PersonService personService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	Person personCreate(@RequestHeader(value = "Authorization", required=true) String credentials ,
			@RequestBody Person person){
		return personService.create(credentials, person);
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseBody
	void update(@RequestHeader(value = "Authorization", required=true) String credentials ,
			@RequestBody Person person, @PathVariable("id") Long id) {
		personService.update(credentials, person, id);
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	Person read(@RequestHeader(value = "Authorization", required=true) String credentials, @PathVariable("id") Long id){
		return personService.read(credentials, id);
		
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	void delete(@RequestHeader(value = "Authorization", required=true) String credentials, @PathVariable("id") Long id){
		personService.destroy(credentials, id);
		
	}
}
