package ua.com.foxminded.javaspring.SchoolApplication.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.javaspring.SchoolApplication.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	boolean create(Course course);

	boolean createAll(List<Course> e);

	@Modifying
	@Query("delete from application.course c where c = :course")
	boolean deleteCourse(@Param("course") Course course);

	boolean update(Course course);

	Optional<Course> findById(Long key);

	List<Course> findByTitle(String title);

	List<Course> findAll();

	boolean ifExistFindById(Long key);
}
