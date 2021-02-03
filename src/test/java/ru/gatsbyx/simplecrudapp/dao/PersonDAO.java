package ru.gatsbyx.simplecrudapp.dao;

import ru.gatsbyx.simplecrudapp.models.Person;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:db.properties")
public class PersonDAO {
	private static int PEOPLE_COUNT;
	
	@Value("${host}")
	private String host;
	
	@Value("${login}")
	private String login;
	
	@Value("${password}")
	private String password;
	
	private Connection connection;
	
	public PersonDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(host);
			connection = DriverManager.getConnection(host, login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Person> index() {
		List<Person> people = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String sqlQuery = "select * from person";
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			
			while(resultSet.next()) {
				Person person = new Person();
				
				person.setId(resultSet.getInt("id"));
				person.setAge(resultSet.getInt("age"));
				person.setName(resultSet.getString("name"));
				person.setEmail(resultSet.getString("email"));
				
				people.add(person);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return people;
	}
	
	public Person get(int id) {
		return null;
	//	return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
	}
	
	public void update(int id, Person updatedPerson) {
	//	Person personToUpdate = get(id);
		
	//	personToUpdate.setAge(updatedPerson.getAge());
	//	personToUpdate.setName(updatedPerson.getName());
	//	personToUpdate.setEmail(updatedPerson.getEmail());
	}
	
	public void save(Person person) {
		person.setId(++PEOPLE_COUNT);
		//people.add(person);
	}
	
	public void delete(int id) {
		//people.removeIf(person -> person.getId() == id);
	}
}
