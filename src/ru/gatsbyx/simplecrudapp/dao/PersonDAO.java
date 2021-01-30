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
		people.add(new Person(++PEOPLE_COUNT, "Kyligina"));
		people.add(new Person(++PEOPLE_COUNT, "Andrey"));
		people.add(new Person(++PEOPLE_COUNT, "Lobaev"));
	}
	
	public List<Person> index() {
		return people;
	}
	
	public Person show(int id) {
		return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
	}
}
