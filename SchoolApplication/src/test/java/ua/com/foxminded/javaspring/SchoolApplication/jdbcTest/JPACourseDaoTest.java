package ua.com.foxminded.javaspring.SchoolApplication.jdbcTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.CourseRepository;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;

@SpringBootTest(classes = { EntityManager.class, PostgreSqlCourseDao.class, EntityManagerFactory.class })
class JPACourseDaoTest {

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private PostgreSqlCourseDao postgreSqlCourseDao;

	private CourseRepository courseRepository;

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
	void setUp() {

		postgreSqlCourseDao = new PostgreSqlCourseDao(courseRepository);
	}

	@Test
	void testCreateCourse() {

		PostgreSqlCourseDao postgreSqlCourseDao = mock(PostgreSqlCourseDao.class);

		Course newCourse = new Course();

		newCourse.setKey(5L);
		newCourse.setTitle("Programing");
		newCourse.setDescription("Java");

		when(postgreSqlCourseDao.createAll(coursesList)).thenReturn(true);

		postgreSqlCourseDao.create(newCourse);

		assertEquals(newCourse.getTitle(), "Programing");
		assertEquals(newCourse.getDescription(), "Java");

		verify(postgreSqlCourseDao).create(any(Course.class));
	}

	@Test
	void testCreateAllCourses() {

		PostgreSqlCourseDao postgreSqlCourseDao = mock(PostgreSqlCourseDao.class);

		List<Course> courseslist = new ArrayList<>();

		Course courseNewOne = new Course(11L, "English", "Present Simple");
		Course courseNewTwo = new Course(12L, "Art", "3D Sculpture");

		courseslist.add(courseNewOne);
		courseslist.add(courseNewTwo);

		when(postgreSqlCourseDao.createAll(coursesList)).thenReturn(true);

		List<Course> newCoursesList = List.of(courseNewOne, courseNewTwo);
		postgreSqlCourseDao.createAll(newCoursesList);

		assertEquals(newCoursesList.get(0).getTitle(), courseNewOne.getTitle());

		verify(postgreSqlCourseDao).createAll(newCoursesList);
	}

	@Test
	void testDeleteCourse() {

		PostgreSqlCourseDao postgreSqlCourseDao = mock(PostgreSqlCourseDao.class);

		courseTest.setTitle("Sixth");
		courseTest.setDescription("Programming");

		when(postgreSqlCourseDao.deleteCourse(courseTest)).thenReturn(true);

		postgreSqlCourseDao.deleteCourse(courseTest);

		assertTrue(postgreSqlCourseDao.deleteCourse(courseTest));
	}

	@Test
	void testUpdateCourse() {

		PostgreSqlCourseDao postgreSqlCourseDao = mock(PostgreSqlCourseDao.class);

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

		Course expected = new Course();
		expected.setKey(1L);
		Mockito.when(entityManager.find(Mockito.eq(Course.class), Mockito.any())).thenReturn(expected);
		Optional<Course> actual = postgreSqlCourseDao.findById(1L);
		assertEquals(expected, actual);
	}

	@Test
	void testFindAll() {

		TypedQuery<Course> typedQuery = (TypedQuery<Course>) mock(TypedQuery.class);

		when(entityManager.createQuery(Mockito.anyString(), Mockito.eq(Course.class))).thenReturn(typedQuery);
		when(typedQuery.getResultList()).thenReturn(coursesList);

		List<Course> result = postgreSqlCourseDao.findAll();

		assertEquals(coursesList, result);

		verify(typedQuery, Mockito.times(1)).getResultList();
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
