package ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.dao.GroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;
import ua.com.foxminded.javaspring.SchoolApplication.model.GroupMapper;

@Service
public class PostgreSqlGroupDao implements GroupDao {

	private final JdbcTemplate jdbcTemplate;

	private static final String SQL_CREATE_GROUP = " insert into groups (group_id, title) " + " values (?, ?) ";
	private static final String SQL_DELETE_GROUP = " delete from groups " + " where group_id = ? ";
	private static final String SQL_UPDATE_GROUP = " update groups set title = ? " + " where group_id = ? ";
	private static final String SQL_FIND_GROUP_BY_ID = " select * from groups " + " where group_id = ? ";
	private static final String SQL_FIND_GROUP_BY_TITLE = " select * from groups " + " where title = ? ";
	private static final String SQL_FIND_ALL = " select * from groups";

	public PostgreSqlGroupDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
			GroupMapper groupMapper) {

		this.jdbcTemplate = jdbcTemplate;

	}

	public boolean create(Group group) {
		return jdbcTemplate.update(SQL_CREATE_GROUP, group.getKey(), group.getTitle()) > 0;
	}

	public boolean update(Group group) {
		return jdbcTemplate.update(SQL_UPDATE_GROUP, group.getTitle(), group.getKey()) > 0;
	}

	public boolean delete(Group group) {
		return jdbcTemplate.update(SQL_DELETE_GROUP, group.getKey()) > 0;
	}

	@Override
	public boolean ifExistFindById(Long key) {
		return jdbcTemplate.queryForObject(SQL_FIND_GROUP_BY_ID, new Object[] { key }, new GroupMapper()) != null;
	}

	@Override
	public List<Entity> findByTitle(String title) {
		return (List<Entity>) jdbcTemplate.queryForObject(SQL_FIND_GROUP_BY_TITLE, new Object[] { title },
				new GroupMapper());
	}

	@Override
	public Group findById(Long key) {
		return (Group) jdbcTemplate.queryForObject(SQL_FIND_GROUP_BY_ID, new Object[] { key }, new GroupMapper());
	}

	public List<Entity> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, new GroupMapper());
	}
}
