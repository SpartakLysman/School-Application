package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class CourseService {

	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	private PostgreSqlCourseDao courseRepository;

	public boolean create(Course course) {

		LOGGER.debug("Course creating - " + course.toString());
		boolean created = courseRepository.create(course);
		LOGGER.info("Course was created successfully with id - " + course.getKey());

		return created;
	}

	public int[] createAll(List<Course> coursesList) {

		LOGGER.debug("All courses creating...");
		int[] createdAll = courseRepository.createAll(coursesList);
		LOGGER.info("All courses were successfully created - " + coursesList.toString());

		return createdAll;
	}

	public List<Course> findAll() {

		LOGGER.debug("All courses finding...");
		List<Course> coursesList = courseRepository.findAll();
		LOGGER.info("All courses were successfully found");

		return coursesList;
	}

	public Course findById(long key) {

		LOGGER.debug("Course finding - " + key);
		Course course = courseRepository.findById(key);
		LOGGER.info("Course was successfully found by id - " + key);

		return course;

	}

	public List<Course> findByTitle(String title) {

		LOGGER.debug("Courses finding by title");
		List<Course> coursesList = courseRepository.findByTitle(title);
		LOGGER.info("Courses were successfully found by title - " + title);

		return coursesList;
	}

	public boolean delete(Course course) {

		LOGGER.debug("Course deleting - " + course.toString());
		boolean deleted = courseRepository.delete(course);
		LOGGER.info("Course successfully deleted with id - " + course.getKey());

		return deleted;
	}

	public boolean update(Course course) {

		LOGGER.debug("Course updating - " + course.toString());
		boolean updated = courseRepository.update(course);
		LOGGER.info("Course was successfully updated with id - " + course.getKey());

		return updated;
	}
}
