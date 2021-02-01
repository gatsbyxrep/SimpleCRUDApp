package ru.gatsbyx.simplecrudapp.dao;

import ru.gatsbyx.simplecrudapp.models.Person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PersonDAO {
	private static int PEOPLE_COUNT;
	private List<Person> people;
	
	{
		people = new ArrayList<>();
		people.add(new Person(++PEOPLE_COUNT, "Alex"));
		people.add(new Person(++PEOPLE_COUNT, "John"));
		people.add(new Person(++PEOPLE_COUNT, "Jim"));
	}
	
	public List<Person> index() {
		return people;
	}
	
	public Person get(int id) {
		return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
	}
	
	public void update(int id, Person updatedPerson) {
		get(id).copyFrom(updatedPerson);
	}
	
	public void save(Person person) {
		person.setId(++PEOPLE_COUNT);
		people.add(person);
	}
	
	public void delete(int id) {
		people.removeIf(person -> person.getId() == id);
	}
}
