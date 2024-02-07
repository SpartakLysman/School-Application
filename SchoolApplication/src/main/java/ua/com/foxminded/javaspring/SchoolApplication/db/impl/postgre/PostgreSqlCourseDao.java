package ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.CourseRepository;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;

@Repository
@Transactional
public class PostgreSqlCourseDao {

	@PersistenceContext
	private EntityManager entityManager;

	private final CourseRepository courseRepository;

	@Autowired
	public PostgreSqlCourseDao(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public boolean create(Course course) {
		try {
			courseRepository.save(course);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean createAll(List<Course> coursesList) {
		try {
			courseRepository.saveAll(coursesList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCourse(Course course) {
		try {
			courseRepository.delete(course);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(Course course) {
		try {
			courseRepository.save(course);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Course> findByTitle(String title) {
		return courseRepository.findByTitle(title);
	}

	public Optional<Course> findById(Long key) {
		return courseRepository.findById(key);
	}

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public boolean ifExistFindById(Long key) {
		return courseRepository.existsById(key);
	}
}
