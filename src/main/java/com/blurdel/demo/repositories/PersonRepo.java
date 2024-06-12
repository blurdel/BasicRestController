package com.blurdel.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blurdel.demo.model.Person;

public interface PersonRepo extends JpaRepository <Person, Long> {

}
