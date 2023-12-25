package ua.com.foxminded.javaspring.SchoolApplication.dao;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

public interface GroupDao extends DAO<Group, Long> {

	List<Group> findAll();

	List<Group> findAll(long id);

}
