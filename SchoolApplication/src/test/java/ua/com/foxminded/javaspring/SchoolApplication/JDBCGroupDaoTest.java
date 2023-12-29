package ua.com.foxminded.javaspring.SchoolApplication;

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

@Sql(scripts = { "/sql/clear_tables.sql", "/sql/Database.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

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
	private List<Group> groupList;
	private Group groupFirst;
	private Group groupTest;

	{
		groupList = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			Group group = new Group();
			group.setKey((long) i);
			group.setTitle("Gr" + i);
			groupList.add(group);
		}
		groupFirst = groupList.get(0);
		groupTest = new Group();
		groupTest.setKey(10L);
		groupTest.setTitle("Gr10");
	}

	@BeforeEach
	void setUp() throws DaoException {
		jdbcTemplate.setDataSource(dataSource);
		postgreSqlGroupDao = new PostgreSqlGroupDao(jdbcTemplate, namedParameterJdbcTemplate, groupMapper);
	}

	@Test
	void testGetObgect_InGroupId_OutGroupObject() {
		assertEquals(groupFirst, postgreSqlGroupDao.findById(1L));
		assertEquals(null, postgreSqlGroupDao.findById(10L));
	}

	@Test
	void testGetAll_OutGroupsList() {
		assertEquals(groupList, postgreSqlGroupDao.findAll());
	}

	@Test
	void testAddObject_InGroupObject_OutGroupObject() {
		assertEquals(groupTest, postgreSqlGroupDao.create(groupTest));
		postgreSqlGroupDao.delete(groupTest);
	}

	@Test
	void testUpdate_InGroupObject_OutGroupObject() {
		groupFirst.setTitle("Gr10");
		assertEquals(groupFirst, postgreSqlGroupDao.update(groupFirst));
		groupFirst.setTitle("Gr1");
		assertEquals(groupFirst, postgreSqlGroupDao.update(groupFirst));
	}

	@Test
	void testDeleteObject_InGroupObject_OutBoolean() {
		postgreSqlGroupDao.create(groupTest);
		assertTrue(postgreSqlGroupDao.delete(groupTest));
		assertFalse(postgreSqlGroupDao.delete(groupTest));
	}

	@Test
	void testCheckIfExist_InIdGroup_OutBoolean() {
		assertTrue(postgreSqlGroupDao.ifExistfindById(3L));
		assertFalse(postgreSqlGroupDao.ifExistfindById(10L));
	}
}
