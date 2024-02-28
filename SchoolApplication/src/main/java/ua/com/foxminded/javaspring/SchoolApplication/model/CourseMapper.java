package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper implements RowMapper<Course> {

	public Course mapRow(ResultSet resultSet, int i) throws SQLException {
		Course course = new Course();
		course.setId(resultSet.getLong("course_id"));

		course.setTitle(resultSet.getString("title"));
		course.setDescription(resultSet.getString("description"));

		return course;
	}
}
