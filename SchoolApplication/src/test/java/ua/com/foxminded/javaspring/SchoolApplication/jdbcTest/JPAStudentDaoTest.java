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
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		PostgreSqlStudentDao.class }))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = { "/clear_tables.sql", "/test_data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class JPAStudentDaoTest {

	@PersistenceContext
	private EntityManager entityManager;

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

	@BeforeEach
	void setUp() throws DaoException {
		postgreSqlStudentDao = new PostgreSqlStudentDao(entityManager);
	}

	@Test
	void testFindById() {
		assertEquals(studentFirst, postgreSqlStudentDao.findById(1L));
	}

	@Test
	void testFindAll() {
		assertEquals(studentsList, postgreSqlStudentDao.findAll());
	}

	@Test
	void testCreateStudent() {
		studentTest.setKey(6L);
		studentTest.setName("Anton");
		studentTest.setSurname("Antonovich");
		studentTest.setLogin("6666");
		studentTest.setPassword("Sixth");

		assertEquals(true, postgreSqlStudentDao.create(studentTest));
		postgreSqlStudentDao.delete(studentTest);

	}

	@Test
	void testUpdateStudent() {
		studentTest.setKey(1L);
		studentTest.setName("Alex");
		studentTest.setSurname("Alexandrovich");
		studentTest.setLogin("1111");
		studentTest.setPassword("First");

		assertEquals(true, postgreSqlStudentDao.update(studentTest));
	}

	@Test
	void testDeleteStudent() {
		studentTest.setName("Anton");
		studentTest.setSurname("Antonovich");
		studentTest.setLogin("6666");
		studentTest.setPassword("Sixth");

		postgreSqlStudentDao.create(studentTest);
		assertTrue(postgreSqlStudentDao.delete(studentTest));
		assertFalse(postgreSqlStudentDao.delete(studentTest));
	}

	@Test
	void testCheckIfExistByID() {
		assertTrue(postgreSqlStudentDao.ifExistFindById(3L));
	}
}
