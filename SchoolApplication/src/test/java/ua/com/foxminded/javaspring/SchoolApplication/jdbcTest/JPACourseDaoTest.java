package ua.com.foxminded.javaspring.SchoolApplication.jdbcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ua.com.foxminded.javaspring.SchoolApplication.db.dao.DaoException;
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		PostgreSqlCourseDao.class }))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = { "/clear_tables.sql", "/test_data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class JPACourseDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

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
			course.setDescription(course.getDescription());

			coursesList.add(course);
		}
		courseFirst = coursesList.get(0);
		courseTest = new Course();
		courseTest.setKey(6L);

	}

	@BeforeEach
	void setUp() throws DaoException {
		postgreSqlCourseDao = new PostgreSqlCourseDao(entityManager);
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
		courseTest.setDescription("Math");

		assertEquals(true, postgreSqlCourseDao.create(courseTest));
		postgreSqlCourseDao.delete(courseTest);
	}

	@Test
	void testUpdateStudent() {
		courseTest.setKey(1L);
		courseTest.setTitle("First");
		courseTest.setDescription("History");

		assertEquals(true, postgreSqlCourseDao.update(courseTest));
	}

	@Test
	void testDeleteStudent() {
		courseTest.setTitle("Sixth");
		courseTest.setDescription("Programming");

		postgreSqlCourseDao.create(courseTest);
		assertTrue(postgreSqlCourseDao.delete(courseTest));
		assertFalse(postgreSqlCourseDao.delete(courseTest));
	}

	@Test
	void testCheckIfExistByID() {
		assertTrue(postgreSqlCourseDao.ifExistFindById(3L));
	}
}
