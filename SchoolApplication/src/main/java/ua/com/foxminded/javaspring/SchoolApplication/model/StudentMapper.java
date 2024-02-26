package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements RowMapper<Student> {

	public Student mapRow(ResultSet resultSet, int i) throws SQLException {
		Student student = new Student();
		student.setKey(resultSet.getLong("students_id"));
		student.setGroupId(resultSet.getLong("group_id"));
		student.setName(resultSet.getString("name"));
		student.setSurname(resultSet.getString("surname"));
		student.setLogin(resultSet.getString("login"));
		student.setPassword(resultSet.getString("password"));

		return student;
	}
}
