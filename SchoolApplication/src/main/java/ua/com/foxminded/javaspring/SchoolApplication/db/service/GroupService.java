package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlGroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

@Service
public class GroupService {

	@Autowired
	private PostgreSqlGroupDao groupRepository;

	public boolean create(Group group) {

		return groupRepository.create(group);
	}

	public int[] createAll(List<Group> groupsList) {

		return groupRepository.createAll(groupsList);
	}

	public List<Entity> findAll() {

		return groupRepository.findAll();
	}

	public Group findById(long key) {
		return groupRepository.findById(key);
	}

	public List<Group> findByTitle(String title) {

		return groupRepository.findByTitle(title);
	}

	public String delete(Group group) {

		groupRepository.delete(group);
		return "Group was succesfully removed!!";
	}

	public boolean update(Group group) {

		return groupRepository.update(group);
	}
}
