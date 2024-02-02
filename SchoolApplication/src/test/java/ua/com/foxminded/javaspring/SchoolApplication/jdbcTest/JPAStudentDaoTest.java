package ua.com.foxminded.javaspring.SchoolApplication.jdbcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EntityManager.class, PostgreSqlStudentDao.class, EntityManagerFactory.class })
@Sql(scripts = { "/clear_tables.sql", "/test_data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class JPAStudentDaoTest {

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private DataRepository repository;

	private PostgreSqlStudentDao postgreSqlStudentDao;
	private List<Student> studentsList;
	private Student studentFirst;
	private Student studentTest;

	{
		studentsList = new ArrayList<>();
		for (int i = 1; i < 5; i++) {
			Student student = new Student();
			student.setKey((long) i);
			student.setGroupId(student.getGroupId());
			student.setName(student.getName());
			student.setSurname(student.getSurname());
			student.setLogin(student.getLogin());
			student.setPassword(student.getPassword());
			studentsList.add(student);
		}
		studentFirst = studentsList.get(0);
		studentTest = new Student();
		studentTest.setKey(6L);

	}

	@Before
	void setUp() {
		Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
	}

	@Test
	void testCreateStudent() {

		studentTest.setKey(6L);
		studentTest.setName("Anton");
		studentTest.setSurname("Antonovich");
		studentTest.setLogin("6666");
		studentTest.setPassword("Sixth");

		when(postgreSqlStudentDao.create(studentTest)).thenReturn(true);

		boolean isCreated = postgreSqlStudentDao.create(studentTest);

		assertTrue(isCreated);

		verify(postgreSqlStudentDao, Mockito.times(1)).create(studentTest);

		postgreSqlStudentDao.delete(studentTest);
	}

	@Test
	void testCreateAllCourses() {

		List<Student> studentslist = new ArrayList<>();

		Student studentNewOne = new Student(11L, "Alexey", "Dilot", "loginFirst", "passwordFirst");
		Student studentNewTwo = new Student(12L, "Dmitriy", "Sanikolyn", "loginSecond", "passwordSecond");

		studentslist.add(studentNewOne);
		studentslist.add(studentNewTwo);

		when(postgreSqlStudentDao.createAll(studentslist)).thenReturn(true);

		List<Student> newStudentsList = List.of(studentNewOne, studentNewTwo);
		boolean isCreated = postgreSqlStudentDao.createAll(newStudentsList);

		assertEquals(newStudentsList.get(0).getName(), studentNewOne.getName());
		assertEquals(newStudentsList.get(1).getName(), studentNewTwo.getName());

		verify(postgreSqlStudentDao).createAll(newStudentsList);
	}

	@Test
	void testDeleteStudent() {

		studentTest.setName("Anton");
		studentTest.setSurname("Antonovich");
		studentTest.setLogin("6666");
		studentTest.setPassword("Sixth");

		when(postgreSqlStudentDao.create(studentTest)).thenReturn(true);
		when(postgreSqlStudentDao.delete(studentTest)).thenReturn(true);

		assertTrue(postgreSqlStudentDao.create(studentTest));
		assertTrue(postgreSqlStudentDao.delete(studentTest));

		verify(postgreSqlStudentDao, Mockito.times(1)).delete(studentTest);

		assertFalse(postgreSqlStudentDao.delete(studentTest));
	}

	@Test
	void testUpdateStudent() {

		studentTest.setKey(1L);
		studentTest.setName("Alex");
		studentTest.setSurname("Alexandrovich");
		studentTest.setLogin("1111");
		studentTest.setPassword("First");

		when(postgreSqlStudentDao.update(studentTest)).thenReturn(true);

		boolean isUpdated = postgreSqlStudentDao.update(studentTest);

		assertTrue(isUpdated);

		verify(postgreSqlStudentDao, Mockito.times(1)).update(studentTest);
	}

	@Test
	void testFindByName() {

	}

	@Test
	void testFindById() {
		
	   when(postgreSqlStudentDao.findById(1L)).thenReturn(studentFirst);

	   Student studentResult = postgreSqlStudentDao.findById(1L);

	   assertEquals(studentFirst, studentResult);

	   verify(postgreSqlStudentDao, Mockito.times(1)).findById(1L);
	}

	@Test
	void testFindAll() {
		
		when(postgreSqlStudentDao.findAll()).thenReturn(studentsList);

	    List<Student> result = postgreSqlStudentDao.findAll();

	    assertEquals(studentsList, result);

	    verify(postgreSqlStudentDao, Mockito.times(1)).findAll();   
	}

	@Test
	void testCheckIfExistByID() {
		
		when(postgreSqlStudentDao.ifExistFindById(3L)).thenReturn(true);

	    boolean isExist = postgreSqlStudentDao.ifExistFindById(3L);

	    assertTrue(isExist);

        verify(postgreSqlStudentDao, Mockito.times(1)).ifExistFindById(3L);
		    
		assertTrue(postgreSqlStudentDao.ifExistFindById(3L));
	}
}
