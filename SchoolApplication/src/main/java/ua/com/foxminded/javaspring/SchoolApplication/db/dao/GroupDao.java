package ua.com.foxminded.javaspring.SchoolApplication.db.dao;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

public interface GroupDao extends DAO<Group, Long> {

	Group findById(Long key);

	List<Entity> findByTitle(String title);
}
