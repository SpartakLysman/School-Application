package ua.com.foxminded.javaspring.SchoolApplication.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.javaspring.SchoolApplication.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	@Modifying
	@Query("DELETE FROM Course c WHERE c = :course")
	boolean deleteCourse(@Param("course") Course course);

	List<Course> findByTitle(String title);
}
