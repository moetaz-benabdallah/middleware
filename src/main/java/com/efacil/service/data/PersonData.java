package com.efacil.service.data;

import java.util.Date;

import com.efacil.serialize.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

@Getter
@Builder
public class PersonData {

	private Long id;

	private String name;

	@JsonSerialize(using=JsonDateSerializer.class)
	private Date birthDate;

	private Date createdTime;

	private Date updatedTime;

	private Boolean activated;

	@Tolerate
	public PersonData() {

	}
}
