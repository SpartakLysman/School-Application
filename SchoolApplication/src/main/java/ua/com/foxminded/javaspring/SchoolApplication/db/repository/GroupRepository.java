package ua.com.foxminded.javaspring.SchoolApplication.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

	@Modifying
	@Query("DELETE FROM Group g WHERE g = :group")
	boolean deleteGroup(@Param("group") Group group);

	@Query("SELECT DISTINCT g FROM Group g LEFT JOIN FETCH g.courses WHERE g.key = :key")
	Optional<Group> findByIdWithCourses(@Param("key") Long key);

	List<Group> findByTitle(String title);
}
