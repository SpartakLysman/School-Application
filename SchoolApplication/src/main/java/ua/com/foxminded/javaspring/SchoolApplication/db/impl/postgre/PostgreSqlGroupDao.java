package ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ua.com.foxminded.javaspring.SchoolApplication.db.dao.GroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;
import ua.com.foxminded.javaspring.SchoolApplication.model.GroupMapper;

public class PostgreSqlGroupDao implements GroupDao {

	JdbcTemplate jdbcTemplate;

	private DataSource dataSource;

	private final String SQL_CREATE_GROUP = " insert into application.groups (group_id, title) " + " values (?, ?) ";
	private final String SQL_DELETE_GROUP = "delete from groups " + " where groups.group_id = ? ";
	private final String SQL_UPDATE_GROUP = "update groups set title = ? " + " where groups.group_id = ?";
	private final String SQL_FIND_GROUP_BY_ID = " select groups * " + " from application.groups "
			+ " where groups.group_id = ? ";
	private final String SQL_FIND_GROUP_BY_TITLE = " select groups * " + " from application.groups "
			+ " where groups.title = ? ";
	private final String SQL_FIND_ALL = "select groups * " + " from application.groups ";

	public PostgreSqlGroupDao(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;

	}

	public boolean create(Group group) {
		return jdbcTemplate.update(SQL_CREATE_GROUP, group.getKey(), group.getTitle()) > 0;
	}

	public boolean update(Group group) {
		return jdbcTemplate.update(SQL_UPDATE_GROUP, group.getKey(), group.getTitle()) > 0;
	}

	public boolean delete(Group group) {
		return jdbcTemplate.update(SQL_DELETE_GROUP, group.getKey()) > 0;
	}

	@Override
	public Group findById(Long key) {
		return (Group) jdbcTemplate.queryForObject(SQL_FIND_GROUP_BY_ID, new Object[] { key }, new GroupMapper());
	}

	@Override
	public List<Entity> findByTitle(String title) {
		return (List<Entity>) jdbcTemplate.queryForObject(SQL_FIND_GROUP_BY_TITLE, new Object[] { title },
				new GroupMapper());
	}

	public List<Entity> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, new GroupMapper());
	}
}
