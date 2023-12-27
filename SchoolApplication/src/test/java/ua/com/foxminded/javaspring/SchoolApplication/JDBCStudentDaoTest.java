package ua.com.foxminded.javaspring.SchoolApplication;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = { "/sql/clear_tables.sql", "/sql/Database.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

@SpringBootTest
//@RunWith(Application.class)
public class JDBCStudentDaoTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private PostgreSqlStudentDao studentDao;

	@BeforeEach
	void setUp() {
		studentDao = new PostgreSqlStudentDao(jdbcTemplate);

	}

	@Test
	public void studentsCount() {
		DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:jdbc/schema.sql").addScript("classpath:jdbc/test-data.sql").build();

		studentDao.setDataSource(dataSource);

		assertEquals(200, studentDao.getCountOfStudents());

	}

	@Test
	public void createStudent() {
		Student student = new Student(201, 7, "Ivan", "Nenkovskiy", "1201", "two-hundred-first");
		boolean isCreated = studentDao.create(student);

		assertEquals(true, isCreated);

	}

	@Test
	public void deleteStudent() {
		Student student = new Student(109, 6, "Harper", "Daniels", "1109", "one-hundred-ninth");
		boolean isDeleted = studentDao.delete(student);

		assertEquals(true, isDeleted);

	}

	@Test
	public void updateStudent() {
		Student student = new Student(109, 6, "Harper", "Daniels", "1109", "one-hundred-ninth");

		boolean isUpdated = studentDao.update(student);

		assertEquals(true, isUpdated);

	}

	@Test
	public void testFindByName() {
		DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:jdbc/schema.sql").addScript("classpath:jdbc/test-data.sql").build();
		studentDao.setDataSource(dataSource);

		List<Student> expectedList = studentDao.findByName("Mia");

		assertEquals(expectedList.size(), 7);
		assertEquals(expectedList.get(0).getName(), "Mia");
	}

	@Test
	public void testFindById() {
		Student expected = studentDao.findById(3L);

		assertEquals(expected.getName(), "Mason");
		assertEquals(expected.getSurname(), "Cooper");
	}

	@Test
	void findAlStudents() {
		List<Entity> actualList = studentDao.findAll();

		assertEquals(actualList.size(), 200);
	}
}
