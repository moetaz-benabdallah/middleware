package com.efacil.service.request;

import java.util.List;

import com.efacil.domain.Person;
import com.efacil.service.data.PersonData;
import com.efacil.web.data.PersonRequest;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface PersonApi {
	@Headers("Authorization: {credentials}")
	@RequestLine("GET /services/rest/connect/v1.3/Projetas.Person")
	List<Person> getAll(@Param("credentials") String credentials); // a revoir

	@Headers("Authorization: {credentials}")
	@RequestLine("GET /services/rest/connect/v1.3/Projetas.Person/{id}")
	PersonData getOne(@Param("credentials") String credentials, @Param("id") Long id);

	@Headers("Authorization: {credentials}")
	@RequestLine("POST /services/rest/connect/v1.3/Projetas.Person")
	PersonRequest create(@Param("credentials") String credentials, PersonRequest person);

	@Headers("Authorization: {credentials}")
	@RequestLine("DELETE /services/rest/connect/v1.3/Projetas.Person/{id}")
	Person delete(@Param("credentials") String credentials, @Param("id") Long id);

	@RequestLine("POST /services/rest/connect/v1.3/Projetas.Person/{id}")
	@Headers({ "Authorization: {credentials}", "X-HTTP-Method-Override: PATCH" })
	Person update(@Param("credentials") String credentials, PersonRequest person, @Param("id") Long id);
}
