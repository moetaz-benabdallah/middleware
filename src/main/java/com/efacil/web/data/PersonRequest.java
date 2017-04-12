package com.efacil.web.data;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class PersonRequest {

	private Long id;
	
	@NotNull
	private String name;

	@NotNull
	private String birthDate;

	@NotNull
	private Boolean activated;
}
