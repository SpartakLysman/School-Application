package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class CourseService {

	@Autowired
	private PostgreSqlCourseDao courseRepository;

	Logger logger = LoggerFactory.getLogger(LoggingController.class);

	@RequestMapping("/")
	public String index() {
		logger.debug("A DEBUG Message");
		logger.info("An INFO Message");
		logger.warn("A WARN Message");
		logger.error("An ERROR Message");

		return "Course Service";
	}

	public boolean create(Course course) {

		return courseRepository.create(course);
	}

	public int[] createAll(List<Course> coursesList) {

		return courseRepository.createAll(coursesList);
	}

	public List<Entity> findAll() {

		return courseRepository.findAll();
	}

	public Course findById(long key) {

		// if (courseRepository.findById(key) != null) {
		return courseRepository.findById(key);
		// }
		// return null;
	}

	public List<Course> findByTitle(String title) {

		return courseRepository.findByTitle(title);
	}

	public String delete(Course course) {

		courseRepository.delete(course);
		return "Course was succesfully removed!!";
	}

	public boolean update(Course course) {

		return courseRepository.update(course);
	}
}
