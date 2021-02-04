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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class PersonDAO {
	private final JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Person> index() {
		// Or PersonMapper
		return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
	}
	
	public Person get(int id) {
		return jdbcTemplate.query("SELECT * FROM Person where id = ?", new BeanPropertyRowMapper<>(Person.class), id)
				.stream().findAny().orElse(null);
	}
	
	public void update(int id, Person updatedPerson) {
		jdbcTemplate.update("update person set name = ?, age = ?, email = ? where id = ?", updatedPerson.getName(), updatedPerson.getAge(), 
				updatedPerson.getEmail(), id);
	}
	
	public void save(Person person) {
		jdbcTemplate.update("INSERT INTO Person Values(default, ?, ?, ?)", person.getName(), person.getAge(), 
				person.getEmail());
	}
	
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Personwhere id = ?", id);	
	}
}
