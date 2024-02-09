package ua.com.foxminded.javaspring.SchoolApplication.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

	@Modifying
	@Query("DELETE FROM Group g WHERE g = :group")
	boolean deleteGroup(@Param("group") Group group);

	List<Group> findByTitle(String title);

}
