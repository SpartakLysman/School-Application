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
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.CourseMapper;

@Sql(scripts = { "/clear_tables.sql", "/test_data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

@JdbcTest
@ContextConfiguration(classes = { PostgreSqlCourseDao.class, CourseMapper.class })
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class JDBCCourseDaoTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private CourseMapper courseMapper;

	private PostgreSqlCourseDao postgreSqlCourseDao;
	private List<Course> courseList;
	private Course courseFirst;
	private Course courseTest;

	{
		courseList = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			Course course = new Course();
			course.setKey((long) i);
			course.setTitle("Cour" + i);
			courseList.add(course);
		}
		courseFirst = courseList.get(0);
		courseTest = new Course();
		courseTest.setKey(10L);
		courseTest.setTitle("Cour10");
	}

	@BeforeEach
	void setUp() throws DaoException {
		jdbcTemplate.setDataSource(dataSource);
		postgreSqlCourseDao = new PostgreSqlCourseDao(jdbcTemplate, namedParameterJdbcTemplate, courseMapper);
	}

	@Test
	void testGetObgect_InGroupId_OutGroupObject() {
		assertEquals(courseFirst, postgreSqlCourseDao.findById(1L));
		assertEquals(null, postgreSqlCourseDao.findById(10L));
	}

	@Test
	void testGetAll_OutGroupsList() {
		assertEquals(courseList, postgreSqlCourseDao.findAll());
	}

	@Test
	void testAddObject_InGroupObject_OutGroupObject() {
		assertEquals(courseTest, postgreSqlCourseDao.create(courseTest));
		postgreSqlCourseDao.delete(courseTest);
	}

	@Test
	void testUpdate_InGroupObject_OutGroupObject() {
		courseFirst.setTitle("Gr10");
		assertEquals(courseFirst, postgreSqlCourseDao.update(courseFirst));
		courseFirst.setTitle("Gr1");
		assertEquals(courseFirst, postgreSqlCourseDao.update(courseFirst));
	}

	@Test
	void testDeleteObject_InGroupObject_OutBoolean() {
		postgreSqlCourseDao.create(courseTest);
		assertTrue(postgreSqlCourseDao.delete(courseTest));
		assertFalse(postgreSqlCourseDao.delete(courseTest));
	}

	@Test
	void testCheckIfExist_InIdGroup_OutBoolean() {
		assertTrue(postgreSqlCourseDao.ifExistfindById(3L));
		assertFalse(postgreSqlCourseDao.ifExistfindById(10L));
	}
}
