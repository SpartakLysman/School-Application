package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlGroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

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

		if (groupRepository.findById(key) != null) {
			return groupRepository.findById(key);
		}
		return null;
	}

	public List<Group> findByTitle(String title) {

		return groupRepository.findByTitle(title);
	}

	public String delete(Group group) {

		groupRepository.delete(group);
		return "Group was succesfully removed!! " + group.getKey();
	}

	public boolean update(Group group) {

		Group existingGroup = groupRepository.findById(group.getKey());
		if (existingGroup == null) {
			return false;
		}

		existingGroup.setKey(group.getKey());
		existingGroup.setTitle(group.getTitle());

		return groupRepository.create(existingGroup);
	}
}
