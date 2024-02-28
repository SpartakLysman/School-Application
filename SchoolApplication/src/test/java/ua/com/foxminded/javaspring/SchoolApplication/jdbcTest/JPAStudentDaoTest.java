package ua.com.foxminded.javaspring.SchoolApplication.jdbcTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.StudentRepository;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@SpringBootTest(classes = { EntityManager.class, PostgreSqlStudentDao.class, EntityManagerFactory.class })
class JPAStudentDaoTest {

	@MockBean
	private EntityManager entityManager;

	@MockBean
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private PostgreSqlStudentDao postgreSqlStudentDao;

	private StudentRepository studentRepository;

	private List<Student> studentsList;
	private Student studentFirst;
	private Student studentTest;

	{
		studentsList = new ArrayList<>();
		for (int i = 1; i < 5; i++) {
			Student student = new Student();
			student.setId((long) i);
			student.setGroupId(student.getGroupId());
			student.setName(student.getName());
			student.setSurname(student.getSurname());
			student.setLogin(student.getLogin());
			student.setPassword(student.getPassword());
			studentsList.add(student);
		}

		studentFirst = studentsList.get(0);
		studentTest = new Student();
		studentTest.setId(6L);
	}

	@BeforeEach
	void setUp() {
		postgreSqlStudentDao = new PostgreSqlStudentDao(studentRepository);
	}

	@Test
	void testCreateStudent() {
		PostgreSqlStudentDao postgreSqlStudentDao = mock(PostgreSqlStudentDao.class);

		studentTest.setId(6L);
		studentTest.setName("Anton");
		studentTest.setSurname("Antonovich");
		studentTest.setLogin("6666");
		studentTest.setPassword("Sixth");

		when(postgreSqlStudentDao.create(studentTest)).thenReturn(true);

		boolean isCreated = postgreSqlStudentDao.create(studentTest);

		assertTrue(isCreated);

		verify(postgreSqlStudentDao, Mockito.times(1)).create(studentTest);

		postgreSqlStudentDao.deleteStudent(studentTest);
	}

	@Test
	void testCreateAllCourses() {
		PostgreSqlStudentDao postgreSqlStudentDao = mock(PostgreSqlStudentDao.class);

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
		PostgreSqlStudentDao postgreSqlStudentDao = mock(PostgreSqlStudentDao.class);

		studentTest.setName("Anton");
		studentTest.setSurname("Antonovich");
		studentTest.setLogin("6666");
		studentTest.setPassword("Sixth");

		when(postgreSqlStudentDao.deleteStudent(studentTest)).thenReturn(true);

		assertTrue(postgreSqlStudentDao.deleteStudent(studentTest));

		verify(postgreSqlStudentDao).deleteStudent(studentTest);
	}

	@Test
	void testUpdateStudent() {
		PostgreSqlStudentDao postgreSqlStudentDao = mock(PostgreSqlStudentDao.class);

		studentTest.setId(1L);
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
		Student student1 = new Student();
		student1.setName("Ivan");

		Student student2 = new Student();
		student2.setName("Ivan");

		List<Student> expectedStudent = Arrays.asList(student1, student2);

		TypedQuery<Student> queryMock = mock(TypedQuery.class);
		when(entityManager.createQuery(anyString(), eq(Student.class))).thenReturn(queryMock);
		when(queryMock.setParameter(eq("name"), eq("Ivan"))).thenReturn(queryMock);
		when(queryMock.getResultList()).thenReturn(expectedStudent);

		List<Student> resultStudents = postgreSqlStudentDao.findByName("Ivan");

		assertEquals(expectedStudent, resultStudents);

		verify(entityManager).createQuery(anyString(), eq(Student.class));
		verify(queryMock).setParameter(eq("name"), eq("Ivan"));
		verify(queryMock).getResultList();
	}

	@Test
	void testFindById() {

		Student expected = new Student();
		expected.setId(1L);
		Mockito.when(entityManager.find(Mockito.eq(Student.class), Mockito.any())).thenReturn(expected);
		Optional<Student> actual = postgreSqlStudentDao.findById(1L);
		assertEquals(expected, actual);
	}

	@Test
	void testFindAll() {
		TypedQuery<Student> typedQuery = (TypedQuery<Student>) mock(TypedQuery.class);

		when(entityManager.createQuery(Mockito.anyString(), Mockito.eq(Student.class))).thenReturn(typedQuery);
		when(typedQuery.getResultList()).thenReturn(studentsList);

		List<Student> result = postgreSqlStudentDao.findAll();

		assertEquals(studentsList, result);

		verify(typedQuery, Mockito.times(1)).getResultList();
	}

	@Test
	void testCheckIfExistByID() {
		PostgreSqlStudentDao postgreSqlStudentDao = mock(PostgreSqlStudentDao.class);

		when(postgreSqlStudentDao.ifExistFindById(3L)).thenReturn(true);

		boolean isExist = postgreSqlStudentDao.ifExistFindById(3L);

		assertTrue(isExist);

		verify(postgreSqlStudentDao, Mockito.times(1)).ifExistFindById(3L);

		assertTrue(postgreSqlStudentDao.ifExistFindById(3L));
	}
}
