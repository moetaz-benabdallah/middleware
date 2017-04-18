package com.efacil.web.data;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class PersonRequest {

	private Long id;
	
	@NotNull
	private String name;

	@NotNull
	private Date birthDate;

	@NotNull
	private Boolean activated;
}
