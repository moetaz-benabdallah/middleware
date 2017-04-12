package com.efacil.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.efacil.domain.Person;

@Repository
public interface PeopleRepository extends PagingAndSortingRepository<Person, Long> {
}
