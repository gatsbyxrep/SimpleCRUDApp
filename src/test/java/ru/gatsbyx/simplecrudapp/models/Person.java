package ru.gatsbyx.simplecrudapp.models;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
	private int id;
	
	@Min(value = 1, message = "Incorrect age")
	private int age;
	
	@NotEmpty
	@Size(min = 2, max = 30, message = "Incorrect name")
	private String name;
	

	@NotEmpty
	@Email(message = "Incorrect email")
	private String email;
	
	public Person(int id, int age, String name, String email) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.email = email;
	}

	
	public Person() { }
	
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
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
