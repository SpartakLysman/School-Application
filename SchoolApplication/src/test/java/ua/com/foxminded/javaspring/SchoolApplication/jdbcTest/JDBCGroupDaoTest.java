package ua.com.foxminded.javaspring.SchoolApplication.jdbcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.javaspring.SchoolApplication.db.dao.DaoException;
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlGroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;
import ua.com.foxminded.javaspring.SchoolApplication.model.GroupMapper;

@Sql(scripts = { "/clear_tables.sql", "/test_data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

@JdbcTest
@ContextConfiguration(classes = { PostgreSqlGroupDao.class, GroupMapper.class })
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JDBCGroupDaoTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private GroupMapper groupMapper;

	private PostgreSqlGroupDao postgreSqlGroupDao;
	private List<Group> groupsList;
	private Group groupFirst;
	private Group groupTest;

	{
		groupsList = new ArrayList<>();
		for (int i = 1; i < 5; i++) {
			Group group = new Group();
			group.setKey((long) i);
			group.setTitle(group.getTitle());

			groupsList.add(group);
		}
		groupFirst = groupsList.get(0);
		groupTest = new Group();
		groupTest.setKey(5L);

	}

	@BeforeEach
	void setUp() throws DaoException {
		jdbcTemplate.setDataSource(dataSource);
		postgreSqlGroupDao = new PostgreSqlGroupDao(jdbcTemplate, namedParameterJdbcTemplate, groupMapper);
	}

	@Test
	void testFindById() {
		assertEquals(groupFirst, postgreSqlGroupDao.findById(1L));
	}

	@Test
	void testFindAll() {
		assertEquals(groupsList, postgreSqlGroupDao.findAll());
	}

	@Test
	void testCreateStudent() {
		groupTest.setKey(6L);
		groupTest.setTitle("Sixth");

		assertEquals(true, postgreSqlGroupDao.create(groupTest));
		postgreSqlGroupDao.delete(groupTest);
	}

	@Test
	void testUpdateStudent() {
		groupTest.setKey(1L);
		groupTest.setTitle("Firts");

		assertEquals(true, postgreSqlGroupDao.update(groupTest));
	}

	@Test
	void testDeleteStudent() {
		groupTest.setTitle("First");

		postgreSqlGroupDao.create(groupTest);
		assertTrue(postgreSqlGroupDao.delete(groupTest));
		assertFalse(postgreSqlGroupDao.delete(groupTest));
	}

	@Test
	void testCheckIfExistByID() {
		assertTrue(postgreSqlGroupDao.ifExistFindById(3L));
	}
}
