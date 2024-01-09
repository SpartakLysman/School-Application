package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;

@Service
public class CourseService {

	@Autowired
	private PostgreSqlCourseDao courseRepository;

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

		if (courseRepository.findById(key) != null) {
			return courseRepository.findById(key);
		}
		return null;
	}

	public List<Course> findByTitle(String title) {

		return courseRepository.findByTitle(title);
	}

	public String delete(Course course) {

		courseRepository.delete(course);
		return "Course was succesfully removed!!";
	}

	public boolean update(Course course) {

		Course existingCourse = courseRepository.findById(course.getKey());
		if (existingCourse == null) {
			return false;
		}

		existingCourse.setKey(course.getKey());

		existingCourse.setTitle(course.getTitle());
		existingCourse.setDescribtion(course.getDescribtion());

		return courseRepository.create(existingCourse);
	}
}
