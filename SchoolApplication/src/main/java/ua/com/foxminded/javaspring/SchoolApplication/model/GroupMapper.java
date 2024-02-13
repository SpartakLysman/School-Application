package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper implements RowMapper<Group> {

	public Group mapRow(ResultSet resultSet, int i) throws SQLException {

		Group group = new Group();
		group.setKey(resultSet.getLong("group_id"));

		return group;
	}
}
