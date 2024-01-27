package ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.dao.DaoException;
import ua.com.foxminded.javaspring.SchoolApplication.db.dao.StudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.model.StudentMapper;
import ua.com.foxminded.javaspring.SchoolApplication.model.User;

@Service
public class PostgreSqlStudentDao implements StudentDao {

	private static Logger log = Logger.getLogger(PostgreSqlStudentDao.class.getName());
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	private static final String SQL_CREATE_STUDENT = "insert into application.students (students_id, group_id, name, surname, login, password)"
			+ "	values (?, ?, ?, ?, ?, ?) ";

	private static final String SQL_DELETE_STUDENT = "delete from application.students where students_id = ? ";
	private static final String SQL_UPDATE_STUDENT = "update application.students set group_id = ?, name = ?, surname = ?, login = ?, password = ? "
			+ " where students_id = ? ";
	private static final String SQL_FIND_STUDENT_BY_ID = "select * from application.students "
			+ " where students_id = ? ";
	private static final String SQL_FIND_STUDENT_BY_NAME = " select * from application.students " + " where name = ? ";
	private static final String SQL_FIND_ALL = " select * from application.students ";

	@Autowired
	public PostgreSqlStudentDao(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;

	}

	@Override
	public Student login(String login, String password) throws DaoException {
		log.config("Looking for customer with the login: " + login + " and password: " + password);

		String sql = "SELECT * FROM students WHERE login = ? AND password = ?";

		return jdbcTemplate.query(sql, new Object[] { login, password }, new StudentMapper()).stream().findFirst()
				.orElse(null);
	}

	public boolean create(Student student) {

		return jdbcTemplate.update(SQL_CREATE_STUDENT, student.getKey(), student.getGroupId(), student.getName(),
				student.getSurname(), student.getLogin(), student.getPassword()) > 0;
	}

	public int[] createAll(List<Student> studentsList) {

		List<Object[]> studentRows = new ArrayList<>();

		for (User student : studentsList) {
			studentRows.add(new Object[] { student.getKey(), student.getGroupId(), student.getName(),
					student.getSurname(), student.getLogin(), student.getPassword() });
		}

		return jdbcTemplate.batchUpdate(SQL_CREATE_STUDENT, studentRows);
	}

	public boolean update(Student student) {

		return jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getGroupId(), student.getName(), student.getSurname(),
				student.getLogin(), student.getPassword(), student.getKey()) > 0;
	}

	public boolean delete(Student student) {
		return jdbcTemplate.update(SQL_DELETE_STUDENT, student.getKey()) > 0;
	}

	@Override
	public boolean ifExistFindById(Long key) {
		return jdbcTemplate.queryForObject(SQL_FIND_STUDENT_BY_ID, new Object[] { key }, new StudentMapper()) != null;
	}

	@Override
	public Student findById(Long key) {
		return jdbcTemplate.queryForObject(SQL_FIND_STUDENT_BY_ID, new Object[] { key }, new StudentMapper());
	}

	@Override
	public List<Student> findByName(String name) {
		return (List<Student>) jdbcTemplate.query(SQL_FIND_STUDENT_BY_NAME, new Object[] { name }, new StudentMapper());
	}

	public List<Student> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, new StudentMapper());
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int getCountOfStudents() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM students", Integer.class);
	}
}
