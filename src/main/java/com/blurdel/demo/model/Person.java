package com.blurdel.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private int age;


	public Person() {
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

}
