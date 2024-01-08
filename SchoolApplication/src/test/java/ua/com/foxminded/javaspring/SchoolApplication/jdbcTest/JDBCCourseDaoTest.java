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
	private CourseMapper studentMapper;

	private PostgreSqlCourseDao postgreSqlCourseDao;
	private List<Course> coursesList;
	private Course courseFirst;
	private Course courseTest;

	{
		coursesList = new ArrayList<>();
		for (int i = 1; i < 5; i++) {
			Course course = new Course();
			course.setKey((long) i);
			course.setTitle(course.getTitle());
			course.setDescribtion(course.getDescribtion());

			coursesList.add(course);
		}
		courseFirst = coursesList.get(0);
		courseTest = new Course();
		courseTest.setKey(6L);

	}

	@BeforeEach
	void setUp() throws DaoException {
		jdbcTemplate.setDataSource(dataSource);
		postgreSqlCourseDao = new PostgreSqlCourseDao(jdbcTemplate, namedParameterJdbcTemplate, studentMapper);
	}

	@Test
	void testFindById() {
		assertEquals(courseFirst, postgreSqlCourseDao.findById(1L));
	}

	@Test
	void testFindAll() {
		assertEquals(coursesList, postgreSqlCourseDao.findAll());
	}

	@Test
	void testCreateStudent() {
		courseTest.setKey(6L);
		courseTest.setTitle("Sixth");
		courseTest.setDescribtion("Math");

		assertEquals(true, postgreSqlCourseDao.create(courseTest));
		postgreSqlCourseDao.delete(courseTest);
	}

	@Test
	void testUpdateStudent() {
		courseTest.setKey(1L);
		courseTest.setTitle("First");
		courseTest.setDescribtion("History");

		assertEquals(true, postgreSqlCourseDao.update(courseTest));
	}

	@Test
	void testDeleteStudent() {
		courseTest.setTitle("Sixth");
		courseTest.setDescribtion("Programming");

		postgreSqlCourseDao.create(courseTest);
		assertTrue(postgreSqlCourseDao.delete(courseTest));
		assertFalse(postgreSqlCourseDao.delete(courseTest));
	}

	@Test
	void testCheckIfExistByID() {
		assertTrue(postgreSqlCourseDao.ifExistFindById(3L));
	}
}
