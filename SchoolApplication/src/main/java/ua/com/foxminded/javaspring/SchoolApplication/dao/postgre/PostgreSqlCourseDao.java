package ua.com.foxminded.javaspring.SchoolApplication.dao.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import ua.com.foxminded.javaspring.SchoolApplication.dao.CourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;

public class PostgreSqlCourseDao implements CourseDao {

	private DataSource dataSource;

	public PostgreSqlCourseDao(DataSource dataSource) {

		this.dataSource = dataSource;

	}

	public Course create(Course course) {
		String sql = " insert into application.courses (title, describtion) " + " values (?, ?)";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, course.getTitle());
			preparedStatement.setString(2, course.getDescribtion());

			int update = preparedStatement.executeUpdate();

			if (update == 0) {
				throw new SQLException();
			}

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long key = resultSet.getLong(1);
				course.setKey(key);
				return course;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Course update(Course course) {
		String sql = "update courses set title = ?, describtion = ? " + " where courses.course_id = " + course.getKey();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedSt1 = connection.prepareStatement(sql)) {

			preparedSt1.setString(1, course.getTitle());
			preparedSt1.setString(2, course.getDescribtion());

			int update = preparedSt1.executeUpdate();

			if (update == 0) {
				throw new SQLException();
			}

			return course;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Course delete(Course course) {
		String sql = "delete from courses " + " where courses.course_id =  " + course.getKey();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			int resultSet = preparedStatement.executeUpdate();

			if (resultSet == 0) {
				throw new SQLException();
			}

			return course;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Course> findAll(String id) {

		List<Course> coursesList = new ArrayList<>();

		String sql = " select courses * " + " from application.courses " + " where courses.course_id = ? ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long course_id = resultSet.getLong(1);
				String courseTitle = resultSet.getString(2);
				String courseDescribtion = resultSet.getString(3);

				coursesList.add(new Course(course_id, courseTitle, courseDescribtion));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return coursesList;
	}

	public List<Course> findAll() {
		List<Course> coursesList = new ArrayList<>();

		String sql = "select courses * " + " from application.courses ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long userId = resultSet.getLong(1);
				String courseTitle = resultSet.getString(2);
				String courseDescribtion = resultSet.getString(3);

				Course course = new Course(userId, courseTitle, courseDescribtion);
				coursesList.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return coursesList;
	}
}
