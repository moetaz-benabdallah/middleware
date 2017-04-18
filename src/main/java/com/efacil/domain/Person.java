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
	private Date birthDate;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "updated_time")
	private Date updatedTime;

	private Boolean activated;

	@Tolerate
	public Person() {
	}

	public Person update(String name, Date birthDate, Boolean activated) {
		this.name = name;
		this.birthDate = birthDate;
		this.activated = activated;
		this.updatedTime = new Date();
		return this;
	}
}
