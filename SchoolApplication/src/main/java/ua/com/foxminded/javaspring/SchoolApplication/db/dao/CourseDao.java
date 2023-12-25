package ua.com.foxminded.javaspring.SchoolApplication.db.dao;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;

public interface CourseDao extends DAO<Course, Long> {

	Course findById(Long key);

	List<Entity> findByTitle(String title);
}
