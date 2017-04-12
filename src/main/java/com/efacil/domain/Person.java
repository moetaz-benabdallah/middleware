package com.efacil.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Tolerate;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "people")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String name;

	@Column(name = "birth_date")
	private String birthDate;

	@Column(name = "created_time")
	private String createdTime;

	@Column(name = "updated_time")
	private String updatedTime;

	private Boolean activated;

	@Tolerate
	public Person() {
	}

	public Person update(String name, String birthDate, Boolean activated) {
		this.name = name;
		this.birthDate = birthDate;
		this.activated = activated;
		this.updatedTime = new Date().toLocaleString();
		return this;
	}
}
