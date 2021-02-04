package ru.gatsbyx.simplecrudapp.dao;

import ru.gatsbyx.simplecrudapp.models.Person;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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
	
	
	@PostConstruct
	public void init() {
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
		Person person = null;
		try {
			PreparedStatement preparedStatement = 
					connection.prepareStatement("select * from person where id = ?");
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			person = new Person();
			
			person.setId(resultSet.getInt("id"));
			person.setAge(resultSet.getInt("age"));
			person.setName(resultSet.getString("name"));
			person.setEmail(resultSet.getString("email"));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return person;
	}
	
	public void update(int id, Person updatedPerson) {
		try {
			PreparedStatement preparedStatement = 
					connection.prepareStatement("update Person set name = ?, age = ?, email = ? where id = ?"); 
			preparedStatement.setString(1, updatedPerson.getName());
			preparedStatement.setInt(2, updatedPerson.getAge());
			preparedStatement.setString(3, updatedPerson.getEmail());
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void save(Person person) {
		try {
			PreparedStatement preparedStatement = 
					connection.prepareStatement("insert into person values(1, ?, ?, ?)"); 
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getAge());
			preparedStatement.setString(3, person.getEmail());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void delete(int id) {
		try {
			PreparedStatement preparedStatement =
					connection.prepareStatement("DELETE FROM person where id = ?");
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
