package ua.com.foxminded.javaspring.SchoolApplication.jdbcTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EntityManager.class, PostgreSqlCourseDao.class, EntityManagerFactory.class })
@Sql(scripts = { "/clear_tables.sql", "/test_data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class JPACourseDaoTest {

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private DataRepository repository;

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

	@Before
	void setUp() {
		Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
	}

	@Test
	void testCreateCourse() {

		Course newCourse = new Course();

		newCourse.setKey(5L);
		newCourse.setTitle("Programing");
		newCourse.setDescription("Java");

		when(postgreSqlCourseDao.create(newCourse)).thenReturn(true);

		boolean isCreated = postgreSqlCourseDao.create(newCourse);

		assertTrue(isCreated);
		assertEquals(newCourse.getTitle(), "Programing");
		assertEquals(newCourse.getDescription(), "Java");

		verify(postgreSqlCourseDao).create(any(Course.class));
	}

	@Test
	void testCreateAllCourses() {

		List<Course> courseslist = new ArrayList<>();

		Course courseNewOne = new Course(11L, "English", "Present Simple");
		Course courseNewTwo = new Course(12L, "Art", "3D Sculpture");

		courseslist.add(courseNewOne);
		courseslist.add(courseNewTwo);

		when(postgreSqlCourseDao.createAll(courseslist)).thenReturn(true);

		List<Course> newCoursesList = List.of(courseNewOne, courseNewTwo);
		boolean isCreated = postgreSqlCourseDao.createAll(newCoursesList);

		assertEquals(newCoursesList.get(0).getTitle(), courseNewOne.getTitle());
		assertEquals(newCoursesList.get(1).getTitle(), courseNewTwo.getTitle());

		verify(postgreSqlCourseDao).createAll(newCoursesList);
	}

	@Test
	void testDeleteCourse() {

		courseTest.setTitle("Sixth");
		courseTest.setDescription("Programming");

		when(postgreSqlCourseDao.delete(courseTest)).thenReturn(true);

		boolean isDeleted = postgreSqlCourseDao.create(courseTest);

		assertTrue(isDeleted);
		assertFalse(postgreSqlCourseDao.delete(courseTest));

		verify(postgreSqlCourseDao).update(any(Course.class));
	}

	@Test
	void testUpdateCourse() {

		courseTest.setKey(1L);
		courseTest.setTitle("First");
		courseTest.setDescription("History");

		when(postgreSqlCourseDao.update(courseTest)).thenReturn(true);

		boolean isUpdated = postgreSqlCourseDao.update(courseTest);

		assertTrue(isUpdated);

		verify(postgreSqlCourseDao).update(any(Course.class));
	}

	@Test
	void testFindByTitle() {

		Course course1 = new Course();
		course1.setTitle("Math");
		course1.setDescription("Mathematics");

		Course course2 = new Course();
		course2.setTitle("Math");
		course2.setDescription("Advanced Mathematics");

		List<Course> expectedCourses = Arrays.asList(course1, course2);

		TypedQuery<Course> queryMock = mock(TypedQuery.class);
		when(entityManager.createQuery(anyString(), eq(Course.class))).thenReturn(queryMock);
		when(queryMock.setParameter(eq("title"), eq("Math"))).thenReturn(queryMock);
		when(queryMock.getResultList()).thenReturn(expectedCourses);

		List<Course> resultCourses = postgreSqlCourseDao.findByTitle("Math");

		assertEquals(expectedCourses, resultCourses);

		verify(entityManager).createQuery(anyString(), eq(Course.class));
		verify(queryMock).setParameter(eq("title"), eq("Math"));
		verify(queryMock).getResultList();
	}

	@Test
	void testFindById() {

		long courseIdToFind = 1L;
		Course expectedCourse = new Course();
		expectedCourse.setKey(courseIdToFind);
		expectedCourse.setTitle("First");
		expectedCourse.setDescription("History");

		when(entityManager.find(Course.class, courseIdToFind)).thenReturn(expectedCourse);

		Course resultCourse = postgreSqlCourseDao.findById(courseIdToFind);

		assertEquals(expectedCourse, resultCourse);

		verify(entityManager).find(Course.class, courseIdToFind);
	}

	@Test
	void testFindAll() {

		TypedQuery<Course> queryMock = mock(TypedQuery.class);
		when(entityManager.createQuery(any(CriteriaQuery.class))).thenReturn(queryMock);
		when(queryMock.getResultList()).thenReturn(coursesList);

		List<Course> resultCourses = postgreSqlCourseDao.findAll();

		assertEquals(coursesList, resultCourses);

		verify(entityManager).createQuery(any(CriteriaQuery.class));
		verify(queryMock).getResultList();

		assertEquals(coursesList, postgreSqlCourseDao.findAll());
	}

	@Test
	void testCheckIfExistByID() {

		long courseIdToCheck = 3L;
		Course existingCourse = new Course();
		existingCourse.setKey(courseIdToCheck);

		when(entityManager.find(Course.class, courseIdToCheck)).thenReturn(existingCourse);

		boolean isExists = postgreSqlCourseDao.ifExistFindById(courseIdToCheck);

		assertTrue(isExists);

		verify(entityManager).find(Course.class, courseIdToCheck);
	}
}
