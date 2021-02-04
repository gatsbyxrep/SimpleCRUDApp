package ru.gatsbyx.simplecrudapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.gatsbyx.simplecrudapp.models.Person;

// Unused now
public class PersonMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet resultSet, int i) throws SQLException {
		Person person = new Person();
		
		person.setId(resultSet.getInt("id"));
		person.setAge(resultSet.getInt("age"));
		person.setName(resultSet.getString("name"));
		person.setEmail(resultSet.getString("email"));
		
		return person;
		
	}

}
