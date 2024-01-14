package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlGroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class GroupService {

	@Autowired
	private PostgreSqlGroupDao groupRepository;

	Logger logger = LoggerFactory.getLogger(LoggingController.class);

	@RequestMapping("/")
	public String index() {
		logger.debug("A DEBUG Message");
		logger.info("An INFO Message");
		logger.warn("A WARN Message");
		logger.error("An ERROR Message");

		return "Group Service";
	}

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
