package ua.com.foxminded.javaspring.SchoolApplication.db.dao;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Course;

public interface CourseDao extends DAO<Course, Long> {

	Course findById(Long key);

	List<Course> findByTitle(String title);

	boolean ifExistFindById(Long key);
}
