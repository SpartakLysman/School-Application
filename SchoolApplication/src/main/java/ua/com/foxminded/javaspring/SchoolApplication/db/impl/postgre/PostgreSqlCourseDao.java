package ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ua.com.foxminded.javaspring.SchoolApplication.db.dao.CourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;

@Repository
@Transactional
public class PostgreSqlCourseDao implements CourseDao {

	@PersistenceContext
	private EntityManager entityManager;

	private static final String SQL_CREATE_COURSE = " insert into application.courses (course_id, title, description) "
			+ " values (?, ?, ?) ";
	private static final String SQL_DELETE_COURSE = " delete from application.courses " + " where course_id = ? ";
	private static final String SQL_UPDATE_COURSE = " update application.courses set title = ?, description = ? "
			+ " where course_id = ? ";
	private static final String SQL_FIND_COURSE_BY_ID = " select * from application.courses " + " where course_id = ? ";
	private static final String SQL_FIND_COURSE_BY_TITLE = " select courses * from application.courses "
			+ " where title = ? ";
	private static final String SQL_FIND_ALL = " select * from application.courses ";

	@Autowired
	public PostgreSqlCourseDao(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	public boolean create(Course course) {

		try {
			entityManager.persist(course);
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	public boolean createAll(List<Course> coursesList) {

		try {
			for (Course course : coursesList) {
				entityManager.persist(course);
			}
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	public boolean update(Course course) {

		try {
			entityManager.merge(course);
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public boolean delete(Course course) {

		try {

			Course course1 = entityManager.find(Course.class, course.getKey);
			if (course1 != null) {
				entityManager.remove(course1);
				return true;

			} else {

				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean ifExistFindById(Long key) {
		return entityManager.find(Course.class, key) != null;
	}

	@Override
	public List<Course> findByTitle(String title) {
		return entityManager.createQuery(SQL_FIND_COURSE_BY_TITLE, Course.class).setParameter("title", title)
				.getResultList();
	}

	@Override
	public Course findById(Long key) {
		return entityManager.find(Course.class, key);
	}

	public List<Course> findAll() {
		return entityManager.createQuery(SQL_FIND_ALL, Course.class).getResultList();
	}
}
