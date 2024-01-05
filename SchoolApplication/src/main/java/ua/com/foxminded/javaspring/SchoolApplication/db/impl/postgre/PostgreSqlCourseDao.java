package ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.dao.CourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.CourseMapper;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;

@Service
public class PostgreSqlCourseDao implements CourseDao {

	private final JdbcTemplate jdbcTemplate;

	private static final String SQL_CREATE_COURSE = " insert into application.courses (course_id, title, desctibtion) "
			+ " values (?, ?, ?) ";
	private static final String SQL_DELETE_COURSE = "delete from courses " + " where courses.course_id = ? ";
	private static final String SQL_UPDATE_COURSE = "update courses set course_id = ?, title = ?, desctibtion = ? "
			+ " where courses.course_id = ?";
	private static final String SQL_FIND_BY_ID = " select courses.* " + " from application.courses "
			+ " where courses.course_id = ? ";
	private static final String SQL_FIND_BY_TITLE = " select courses.* " + " from application.courses "
			+ " where courses.title = ? ";
	private static final String SQL_FIND_ALL = "select courses.* " + " from application.courses ";

	public PostgreSqlCourseDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
			CourseMapper courseMapper) {

		this.jdbcTemplate = jdbcTemplate;

	}

	public boolean create(Course course) {
		return jdbcTemplate.update(SQL_CREATE_COURSE, course.getKey(), course.getTitle(), course.getDescribtion()) > 0;
	}

	public boolean update(Course course) {
		return jdbcTemplate.update(SQL_UPDATE_COURSE, course.getKey(), course.getTitle(), course.getDescribtion()) > 0;
	}

	public boolean delete(Course course) {
		return jdbcTemplate.update(SQL_DELETE_COURSE, course.getKey()) > 0;
	}

	@Override
	public boolean ifExistfindById(Long key) {
		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { key }, new CourseMapper()) != null;
	}

	@Override
	public Course findById(Long key) {
		return (Course) jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { key }, new CourseMapper());
	}

	@Override
	public List<Entity> findByTitle(String title) {
		return (List<Entity>) jdbcTemplate.queryForObject(SQL_FIND_BY_TITLE, new Object[] { title },
				new CourseMapper());
	}

	public List<Entity> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, new CourseMapper());
	}
}
