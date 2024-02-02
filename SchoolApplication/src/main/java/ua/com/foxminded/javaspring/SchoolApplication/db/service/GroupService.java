package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlGroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class GroupService {

	private PostgreSqlGroupDao groupRepository;

	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	public GroupService(EntityManager entityManager) {

		this.groupRepository = new PostgreSqlGroupDao(entityManager);

	}

	public boolean create(Group group) {

		LOGGER.debug("Group creating - " + group.toString());
		boolean created = groupRepository.create(group);
		LOGGER.info("Group was successfully created with id - " + group.getKey());

		return created;
	}

	public boolean createAll(List<Group> groupsList) {

		LOGGER.debug("All groups creating...");
		boolean createdAll = groupRepository.createAll(groupsList);
		LOGGER.info("All groups were successfully created " + groupsList.toString());

		return createdAll;
	}

	public boolean delete(Group group) {

		LOGGER.debug("Group deleting - " + group.toString());
		boolean deleted = groupRepository.delete(group);
		LOGGER.info("Group was successfully removed with id - " + group.getKey());

		return deleted;
	}

	public boolean update(Group group) {

		LOGGER.debug("Group updating - " + group.toString());
		boolean updated = groupRepository.update(group);
		LOGGER.info("Group was successfully updated with id - " + group.getKey());

		return updated;
	}

	public List<Group> findByTitle(String title) {

		LOGGER.debug("Group finding by title");
		List<Group> groupsList = groupRepository.findByTitle(title);
		LOGGER.info("Groups were successfully found by title - " + title);

		return groupsList;
	}

	public Group findById(long key) {

		LOGGER.debug("Group findind by id");
		Group group = groupRepository.findById(key);
		LOGGER.info("Group was successfully found by id - " + key);

		return group;
	}

	public List<Group> findAll() {

		LOGGER.debug("All groups findind...");
		List<Group> groupsList = groupRepository.findAll();
		LOGGER.info("All groups were successfully found ");

		return groupsList;
	}
}
