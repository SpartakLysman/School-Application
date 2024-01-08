package ua.com.foxminded.javaspring.SchoolApplication.db.dao;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

public interface GroupDao extends DAO<Group, Long> {

	Group findById(Long key);

	List<Group> findByTitle(String title);

	boolean ifExistFindById(Long key);

}
