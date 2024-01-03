package ua.com.foxminded.javaspring.SchoolApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.javaspring.SchoolApplication.db.dao.DaoException;
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlStudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.model.StudentMapper;

@Sql(scripts = { "/sql/clear_tables.sql",
		"/sql/test_data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

@JdbcTest
@ContextConfiguration(classes = { PostgreSqlStudentDao.class, StudentMapper.class })
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JDBCStudentDaoTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private StudentMapper studentMapper;

	private PostgreSqlStudentDao postgreSqlStudentDao;
	private List<Student> studentList;
	private Student studentFirst;
	private Student studentTest;

	{
		studentList = new ArrayList<>();
		for (int i = 1; i < 200; i++) {
			Student student = new Student();
			student.setKey((long) i);
			student.setName("St" + i);
			studentList.add(student);
		}
		studentFirst = studentList.get(0);
		studentTest = new Student();
		studentTest.setKey(200L);
		studentTest.setName("St200");
	}

	@BeforeEach
	void setUp() throws DaoException {
		jdbcTemplate.setDataSource(dataSource);
		postgreSqlStudentDao = new PostgreSqlStudentDao(jdbcTemplate, namedParameterJdbcTemplate, studentMapper);
	}

	@Test
	public void testGetObgect_InGroupId_OutGroupObject() {
		assertEquals(studentFirst, postgreSqlStudentDao.findById(1L));
		assertEquals(null, postgreSqlStudentDao.findById(10L));
	}

	@Test
	public void testGetAll_OutGroupsList() {
		assertEquals(studentList, postgreSqlStudentDao.findAll());
	}

	@Test
	public void testAddObject_InGroupObject_OutGroupObject() {
		assertEquals(studentTest, postgreSqlStudentDao.create(studentTest));
		postgreSqlStudentDao.delete(studentTest);
	}

	@Test
	public void testUpdate_InGroupObject_OutGroupObject() {
		studentFirst.setName("Gr10");
		assertEquals(studentFirst, postgreSqlStudentDao.update(studentFirst));
		studentFirst.setName("Gr1");
		assertEquals(studentFirst, postgreSqlStudentDao.update(studentFirst));
	}

	@Test
	public void testDeleteObject_InGroupObject_OutBoolean() {
		postgreSqlStudentDao.create(studentTest);
		assertTrue(postgreSqlStudentDao.delete(studentTest));
		assertFalse(postgreSqlStudentDao.delete(studentTest));
	}

	@Test
	public void testCheckIfExist_InIdGroup_OutBoolean() {
		assertTrue(postgreSqlStudentDao.ifExistFindById(3L));
		assertFalse(postgreSqlStudentDao.ifExistFindById(10L));
	}
}
