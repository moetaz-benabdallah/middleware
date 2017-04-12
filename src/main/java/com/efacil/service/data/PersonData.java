package com.efacil.service.data;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

@Getter
@Builder
public class PersonData {

	private Long id;

	private String name;

	private String birthDate;

	private String createdTime;

	private String updatedTime;

	private Boolean activated;

	@Tolerate
	public PersonData() {

	}
}
