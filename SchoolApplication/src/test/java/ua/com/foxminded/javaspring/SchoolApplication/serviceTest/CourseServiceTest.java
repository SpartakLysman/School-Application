package ua.com.foxminded.javaspring.SchoolApplication.serviceTest;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.CourseService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;

@SpringBootTest(classes = { CourseService.class })
class CourseServiceTest {

	@MockBean
	PostgreSqlCourseDao postgreSqlCourseDao;

	@Autowired
	CourseService courseService;

	private List<Course> coursesList;
	private Course courseFirst;
	private Course courseTest;
	private int[] size = new int[1];

	{
		Course courseOne = new Course(1L, "Biology", "Animals");
		Course courseTwo = new Course(2L, "Math", "Derivatives");
		Course courseTree = new Course(3L, "Geography", "Japan");
		Course courseFour = new Course(4L, "Geography", "Ukraine");
		Course courseFive = new Course(5L, "Programing", "Java");
		Course courseSix = new Course(6L, "Computer Science", "CPU");
		Course courseSeven = new Course(7L, "Programing", "C++");
		Course courseEight = new Course(8L, "Programing", "Phyton");
		Course courseNine = new Course(9L, "Geography", "Black Sea");
		Course courseTen = new Course(10L, "English", "Present Continues");

		coursesList = List.of(courseOne, courseTwo, courseTree, courseFour, courseFive, courseSix, courseSeven,
				courseEight, courseNine, courseTen);

		courseFirst = coursesList.get(0);
		courseTest = coursesList.get(4);
		size[0] = 10;
	}

	@Test
	void createCourseTest() {
		
		when(postgreSqlCourseDao.create(any(Course.class))).thenReturn(true);

		Course newCourse = new Course(5L, "Programing", "Java");
		boolean isCreated = courseService.create(newCourse);
			
		assertTrue(isCreated);
		assertEquals(newCourse.getTitle(), coursesList.get(4).getTitle());
		assertEquals(newCourse.getDescribtion(), coursesList.get(4).getDescribtion());

		verify(postgreSqlCourseDao).create(any(Course.class));
	}

	@Test
	void createAllCoursesTest() {

		List<Course> courseslist = new ArrayList<>();

		Course courseNewOne = new Course(11L, "English", "Present Simple");
		Course courseNewTwo = new Course(12L, "Art", "3D Sculpture");

		courseslist.add(courseNewOne);
		courseslist.add(courseNewTwo);

		int[] sizeNew = new int[2];

		when(postgreSqlCourseDao.createAll(courseslist)).thenReturn(sizeNew);

		List<Course> newCoursesList = List.of(courseNewOne, courseNewTwo);
		int[] create = courseService.createAll(newCoursesList);

		assertNotNull(create);
		// assertEquals(newStudentsList.size(), create.length);
		assertEquals(newCoursesList.get(0).getTitle(), courseNewOne.getTitle());
		assertEquals(newCoursesList.get(1).getTitle(), courseNewTwo.getTitle());

		verify(postgreSqlCourseDao).createAll(newCoursesList);

	}

	@Test
	void findAllCoursesTest() {

		List<Entity> coursesEntity = new ArrayList<>();

		for (int i = 1; i < coursesList.size(); i++) {
			coursesEntity.add(coursesList.get(i));
		}

		when(postgreSqlCourseDao.findAll()).thenReturn(coursesEntity);

		List<Entity> newCoursesEntity = courseService.findAll();

		assertNotNull(coursesEntity);
		assertEquals(coursesEntity, newCoursesEntity);
		assertEquals(coursesEntity.get(0).getKey(), newCoursesEntity.get(0).getKey());

		verify(postgreSqlCourseDao).findAll();
	}

	@Test
	void findCoursesByIdTest() {	

		when(postgreSqlCourseDao.findById(courseTest.getKey())).thenReturn(coursesList.get(4));

		Course newCourse = courseService.findById(courseTest.getKey());

		assertEquals(newCourse.getTitle(), courseTest.getTitle());
		assertEquals(newCourse.getDescribtion(), courseTest.getDescribtion());

		verify(postgreSqlCourseDao).findById(courseTest.getKey());
	}

	@Test
	void findCoursesByTitleTest() {
		 when(postgreSqlCourseDao.findByTitle(courseTest.getTitle())).thenReturn(List.of(courseTest));

	        List<Course> coursesListByTitle = courseService.findByTitle(courseTest.getTitle());

	        assertNotNull(coursesListByTitle);
	        assertEquals(coursesListByTitle.size(), 1);
	        assertEquals(coursesListByTitle.get(0).getTitle(), courseTest.getTitle());

	        verify(postgreSqlCourseDao).findByTitle(courseTest.getTitle());
	}

	@Test
	void deleteCourseTest() {
		
		when(postgreSqlCourseDao.delete(courseTest)).thenReturn(true);
		
		Course courseOne = new Course(1L, "Biology", "Animals");
		Course courseTwo = new Course(2L, "Math", "Derivatives");
		Course courseTree = new Course(3L, "Geography", "Japan");
		Course courseFour = new Course(4L, "Geography", "Ukraine");
		
		Course courseSix = new Course(6L, "Computer Science", "CPU");
		Course courseSeven = new Course(7L, "Programing", "C++");
		Course courseEight = new Course(8L, "Programing", "Phyton");
		Course courseNine = new Course(9L, "Geography", "Black Sea");
		Course courseTen = new Course(10L, "English", "Present Continues");

		
	List<Course> newCoursesList = List.of(courseOne, courseTwo, courseTree, courseFour, courseSix, courseSeven,
				courseEight, courseNine, courseTen);
	boolean isDeleted = courseService.delete(courseTest);
	
	assertEquals(isDeleted, true);
		assertEquals(newCoursesList.size(), (coursesList.size() - 1));
		//assertNotEquals(newStudentsList, studentsList);
		
		verify(postgreSqlCourseDao).delete(courseTest);
	}

	@Test
	void updateCourseTest() {
		Course courseForCheck = courseTest;

		when(postgreSqlCourseDao.update(courseTest)).thenReturn(true);

		courseTest = new Course(50L, "Swimming", "Fast");
		boolean isUpdated = courseService.update(courseTest);

		assertNotEquals(courseForCheck, courseTest);

		verify(postgreSqlCourseDao).update(courseTest);
	}
}
