package ua.com.foxminded.javaspring.SchoolApplication;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = { "/sql/clear_tables.sql",
		"/sql/sample_data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

@SpringBootTest
public class JDBCStudentDaoTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private PostgreSqlStudentDao studentDao;

	@BeforeEach
	void setUp() {
		studentDao = new PostgreSqlStudentDao(jdbcTemplate);

	}

	/*
	 * @Test public void testFindAll() { List<Entity> expectedList =
	 * entities().map(studentDao::save) .filter(student ->
	 * student.getName().equals("Stark")).toList();
	 * 
	 * List<Entity> actualList = studentDao.findByName("Stark");
	 * 
	 * Assert.assertEquals(expectedList, actualList); }
	 */

	@Test
	public void testFindByName() {
		List<Entity> expectedList = entities().map(studentDao::save).filter(student -> student.getName().equals("Mia"))
				.toList();

		List<Entity> actualList = studentDao.findByName("Mia");

		Assert.assertEquals(expectedList, actualList);
	}

	@Test
	public void testFindById() {
		Student expectedList = entities().map(studentDao::save).filter(student -> student.getKey.equalsIgnoreCase(60L))
				.toList();

		Student actualList = studentDao.findById(2L);
		Assert.assertEquals(expectedList, actualList);
	}
	/*
	 * @Test public void testCreate() { List<Student> expectedList =
	 * entities().map(repository::save) .filter(student ->
	 * student.getLastName().equals("Stark")).toList();
	 * 
	 * List<Student> actualList = repository.findByLastName("Stark");
	 * 
	 * Assert.assertEquals(expectedList, actualList); }
	 * 
	 * @Test public void testDelete() { List<Student> expectedList =
	 * entities().map(repository::save) .filter(student ->
	 * student.getLastName().equalsIgnoreCase("Stark")).toList();
	 * 
	 * List<Student> actualList = repository.findByLastNameIgnoreCase("Stark");
	 * Assert.assertEquals(expectedList, actualList); }
	 * 
	 * 
	 * @Test public void testUpdate() { List<Student> expectedList =
	 * entities().map(repository::save) .filter(student ->
	 * student.getLastName().equalsIgnoreCase("Stark")).toList();
	 * 
	 * List<Student> actualList = repository.findByLastNameIgnoreCase("Stark");
	 * Assert.assertEquals(expectedList, actualList); }
	 */

	/*
	 * private Stream<Student> entities() { return Stream.of(new Student(null,
	 * "Arya", "Stark", 2023), new Student(null, "Jon", "Snow", 2023), new
	 * Student(null, "Rob", "Stark", 2023), new Student(null, "Ned", "stark",
	 * 2023)); }
	 */
}