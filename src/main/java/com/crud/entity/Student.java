package com.crud.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "salary")
	int salary;
	
	@Column(name = "timestamp")
	LocalDateTime timestamp;

	public Student(String name, int salary, LocalDateTime timestamp) {
		super();
		this.name = name;
		this.salary = salary;
		this.timestamp = timestamp;
	}
	
	public Student(int id, String name, int salary, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.timestamp = timestamp;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Student() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}

}
