package ua.com.foxminded.javaspring.SchoolApplication.dao.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import ua.com.foxminded.javaspring.SchoolApplication.dao.DaoException;
import ua.com.foxminded.javaspring.SchoolApplication.dao.DaoFactory;
import ua.com.foxminded.javaspring.SchoolApplication.dao.StudentDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;
import ua.com.foxminded.javaspring.SchoolApplication.model.User;

public class PostgreSqlStudentDao implements StudentDao {

	private DaoFactory daoFactory = new DaoFactory();
	private static Logger log = Logger.getLogger(PostgreSqlStudentDao.class.getName());
	private DataSource dataSource;

	public PostgreSqlStudentDao(DataSource dataSource) {
		this.dataSource = dataSource;
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

	public User create(User student) {

		String sql = " insert into application.students (group_id, name, surname, login, password) "
				+ " values (?, ?, ?, ?, ?) ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setLong(1, student.getGroupId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setString(3, student.getSurname());
			preparedStatement.setString(4, student.getLogin());
			preparedStatement.setString(6, student.getPassword());

			int update = preparedStatement.executeUpdate();

			if (update == 0) {
				throw new SQLException();
			}

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long key = resultSet.getLong(1);
				student.setKey(key);
				return student;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public User update(User student) {

		String sql = "update students set group_id = ?, name = ?, surname = ?, login = ?, password = ? "
				+ " where students.id = " + student.getKey();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedSt1 = connection.prepareStatement(sql)) {

			preparedSt1.setLong(1, student.getGroupId());
			preparedSt1.setString(2, student.getName());
			preparedSt1.setString(3, student.getSurname());
			preparedSt1.setString(4, student.getLogin());
			preparedSt1.setString(5, student.getPassword());

			int update = preparedSt1.executeUpdate();

			if (update == 0) {
				throw new SQLException();
			}

			return student;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public User delete(User student) {
		String sql = "delete from students " + " where students.students_id =  " + student.getKey();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			int resultSet = preparedStatement.executeUpdate();

			if (resultSet == 0) {
				throw new SQLException();
			}

			return student;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student findById(Long key) {
		String sql = " select students * " + " from application.students "
				+ " where students.students.students_id = ? ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setLong(1, (long) key);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				long group_id = resultSet.getLong(2);
				String studentName = resultSet.getString(3);
				String studentSurname = resultSet.getString(4);
				String studentLogin = resultSet.getString(5);
				String studentPassword = resultSet.getString(6);

				return new Student(group_id, studentName, studentSurname, studentLogin, studentPassword);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Student> findByName(String name) {
		List<Student> studentsList = new ArrayList<>();

		String sql = " select students * " + " from application.students " + " where students.name = ? ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long student_id = resultSet.getLong(1);
				long group_id = resultSet.getLong(2);
				String studentName = resultSet.getString(3);
				String studentSurname = resultSet.getString(4);
				String studentLogin = resultSet.getString(5);
				String studentPassword = resultSet.getString(6);

				if (studentName.equals(name)) {

					studentsList.add(new Student(student_id, group_id, studentName, studentSurname, studentLogin,
							studentPassword));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return studentsList;
	}

	public List<User> findAll() {
		List<User> studentsList = new ArrayList<>();

		String sql = "select students * " + " from application.students ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long student_id = resultSet.getLong(1);
				long group_id = resultSet.getLong(2);
				String studentName = resultSet.getString(3);
				String studentSurname = resultSet.getString(4);
				String studentLogin = resultSet.getString(5);
				String studentPassword = resultSet.getString(6);

				studentsList.add(
						new Student(student_id, group_id, studentName, studentSurname, studentLogin, studentPassword));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return studentsList;
	}
}
