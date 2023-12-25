package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GroupMapper implements RowMapper<Entity> {

	public Group mapRow(ResultSet resultSet, int i) throws SQLException {

		Group group = new Group();
		group.setKey(resultSet.getLong("group_id"));
		group.setTitle(resultSet.getString("group_name"));

		return group;
	}
}
