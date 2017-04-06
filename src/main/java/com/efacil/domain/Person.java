package com.efacil.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Tolerate;

import java.io.Serializable;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String birthDate;

    private String createdTime;

    private String updatedTime;

    private Boolean activated;

    @Tolerate
    public Person() {
    }
}
