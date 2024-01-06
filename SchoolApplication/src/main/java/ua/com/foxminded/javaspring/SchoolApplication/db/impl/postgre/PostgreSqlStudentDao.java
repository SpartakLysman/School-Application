package ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.dao.DaoException;
import ua.com.foxminded.javaspring.SchoolApplication.db.dao.StudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.model.StudentMapper;
import ua.com.foxminded.javaspring.SchoolApplication.model.User;

@Service
public class PostgreSqlStudentDao implements StudentDao {

	private static Logger log = Logger.getLogger(PostgreSqlStudentDao.class.getName());
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	private static final String SQL_CREATE_STUDENT = " insert into students (students_id, group_id, name, surname, login, password) "
			+ " values (?, ?, ?, ?, ?, ?) ";
	private static final String SQL_DELETE_STUDENT = "delete from students where students_id = ? ";
	private static final String SQL_UPDATE_STUDENT = "update students set group_id = ?, name = ?, surname = ?, login = ?, password = ? "
			+ " where students_id = ? ";
	private static final String SQL_FIND_STUDENT_BY_ID = "select * from students " + " where students_id = ? ";
	private static final String SQL_FIND_STUDENT_BY_NAME = " select * from students " + " where name = ? ";
	private static final String SQL_FIND_ALL = " select * from students ";

	public PostgreSqlStudentDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
			StudentMapper studentMapper) {

		this.jdbcTemplate = jdbcTemplate;

	}

	@Override
	public Student login(String login, String password) throws DaoException {
		log.config("Looking for customer with the login: " + login + " and password: " + password);

		String sql = " select students * " + " from application.students " + " where students.login = ? "
				+ " and students.password = ? ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				long student_id = resultSet.getLong(1);
				long group_id = resultSet.getLong(2);
				String studentName = resultSet.getString(3);
				String studentSurname = resultSet.getString(4);

				return new Student(student_id, group_id, studentName, studentSurname, login, password);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean create(User student) {

		return jdbcTemplate.update(SQL_CREATE_STUDENT, student.getKey(), student.getGroupId(), student.getName(),
				student.getSurname(), student.getLogin(), student.getPassword()) > 0;
	}

	public boolean update(User student) {

		return jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getGroupId(), student.getName(), student.getSurname(),
				student.getLogin(), student.getPassword(), student.getKey()) > 0;
	}

	public boolean delete(User student) {
		return jdbcTemplate.update(SQL_DELETE_STUDENT, student.getKey()) > 0;
	}

	@Override
	public boolean ifExistFindById(Long key) {
		return jdbcTemplate.queryForObject(SQL_FIND_STUDENT_BY_ID, new Object[] { key }, new StudentMapper()) != null;
	}

	@Override
	public Student findById(Long key) {
		return (Student) jdbcTemplate.queryForObject(SQL_FIND_STUDENT_BY_ID, new Object[] { key }, new StudentMapper());
	}

	@Override
	public List<Student> findByName(String name) {
		return (List<Student>) jdbcTemplate.queryForObject(SQL_FIND_STUDENT_BY_NAME, new Object[] { name },
				new StudentMapper());
	}

	public List<Entity> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, new StudentMapper());
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int getCountOfStudents() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM students", Integer.class);
	}
}
