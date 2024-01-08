package ua.com.foxminded.javaspring.SchoolApplication.serviceTest;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.CourseService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;

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
		

		//when(postgreSqlCourseDao.findByTitle(courseTest.getTitle())).thenReturn(coursesList.);
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
	

		// when(postgreSqlCourseDao.findByTitle(course.getTitle())).thenReturn(coursesList);
		when(postgreSqlCourseDao.createAll(coursesList)).thenReturn(size);

		Course courseNewOne = new Course(11L, "English", "Present Simple");
		Course courseNewTwo = new Course(12L, "Art", "3D Sculpture");

		List<Course> newCoursesList = List.of(courseNewOne, courseNewTwo);
		int[] create = courseService.createAll(newCoursesList);

		assertNotNull(create);
		assertEquals(newCoursesList.size(), create.length);
		assertEquals(newCoursesList.get(0).getTitle(), courseNewOne.getTitle());

		verify(postgreSqlCourseDao).createAll(newCoursesList);
	}

//	@Test
//	void findAllCoursesTest() {
//		Course course;
//		Entity courseFirst;
//
//		List<Entity> courseEntity = new ArrayList<>();
//		
//		for (int i = 1; i < 5; i++) {
//			 course = new Course();
//			course.setKey((long) i);
//			course.setTitle(course.getTitle());
//			course.setDescribtion(course.getDescribtion());
//
//			courseEntity.add(course);
//		}
//		courseFirst = courseEntity.get(0);
//		//courseTest = new Course();
//		//courseTest.setKey(6L);
//		
//		when(postgreSqlCourseDao.findAll()).thenReturn(courseEntity);
//		
//		Course courseOne = new Course(1L, "Biology", "Animals");
//		Course courseTwo = new Course(2L, "Math", "Derivatives");
//		Course courseTree = new Course(3L, "Geography", "Japan");
//		Course courseFour = new Course(4L, "Geography", "Ukraine");
//		Course courseFive = new Course(5L, "Programming", "Java");
//
//		List<Entity> newCoursesList = List.of()
//		CourseDto courseDto = courseService.create(newCourseDto);
//
//		assertNotNull(courseDto.getId());
//		assertEquals(newCourseDto.getName(), courseDto.getName());
//		assertEquals(newCourseDto.getDescription(), courseDto.getDescription());
//
//		verify(postgreSqlCourseDao).findAll().create(any(Course.class));
//	}

	@Test
	void findCoursesByIdTest() {
		

		when(postgreSqlCourseDao.findById(courseTest.getKey())).thenReturn(coursesList.get(4));
		// when(postgreSqlCourseDao.create(any(Course.class))).thenReturn(course);

		Course newCourse = courseService.findById(courseTest.getKey());
		boolean isCreated = courseService.create(newCourse);

		assertTrue(isCreated);
		assertEquals(newCourse.getTitle(), courseTest.getTitle());
		assertEquals(newCourse.getDescribtion(), courseTest.getDescribtion());

		verify(postgreSqlCourseDao).findById(courseTest.getKey());
	}
//
//	@Test
//	void findCoursesByTitleTest() {
//
//		when(postgreSqlCourseDao.findByTitle(courseTest.getTitle())).thenReturn(coursesList)
//		when(postgreSqlCourseDao.create(any(Course.class))).thenReturn(courseFive);
//
//		CourseDto newCourseDto = CourseDto.builder().name("Math").description("Math Description").build();
//		CourseDto courseDto = courseService.create(newCourseDto);
//
//		assertNotNull(courseDto.getId());
//		assertEquals(newCourseDto.getName(), courseDto.getName());
//		assertEquals(newCourseDto.getDescription(), courseDto.getDescription());
//
//		verify(postgreSqlCourseDao).create(any(Course.class));
//	}

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

		

		assertEquals(newCoursesList.size(), coursesList.size() - 1);
		assertNotEquals(newCoursesList, coursesList);
		

		verify(postgreSqlCourseDao).delete(any(Course.class));
	}

	@Test
	void updateCourseTest() {
		Course courseForCheck = courseTest;

		when(postgreSqlCourseDao.update(courseTest)).thenReturn(true);
		when(postgreSqlCourseDao.create(courseTest)).thenReturn(true);

		courseTest = new Course(50L, "Swimming", "Fast");
		boolean isCreated = courseService.create(courseTest);

		assertTrue(isCreated);
		assertNotNull(courseTest);
		assertNotSame(courseForCheck, courseTest);

		verify(postgreSqlCourseDao).update(any(Course.class));
	}
}
