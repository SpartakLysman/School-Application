package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.CourseRepository;
import ua.com.foxminded.javaspring.SchoolApplication.db.repository.GroupRepository;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class CourseService {

	private Group group;

	@PersistenceContext
	private EntityManager entityManager;

	private CourseRepository courseRepository;

	private GroupRepository groupRepository;

	private static final String SQL_ADD_COURSE_TO_GROUP = " insert into application.groups_courses (group_id, course_id) "
			+ "	values (?, ?) ";

	private static final String SQL_DELETE_COURSE_FROM_GROUP = " delete from application.groups_courses where group_id = ? ";

	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	public CourseService() {

		this.group = new Group();

	}

	public boolean create(Course course) {

		LOGGER.debug("Course creating - " + course.toString());
		try {
			courseRepository.save(course);
			LOGGER.info("Course was created successfully with id - " + course.getKey());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean createAll(List<Course> coursesList) {

		LOGGER.debug("All courses creating...");
		try {
			courseRepository.saveAll(coursesList);
			LOGGER.info("All courses were successfully created - " + coursesList.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addCourseToGroup(Course course, long groupId) {

		boolean isCourseNotInGroup = !group.getCourses().contains(course);
		boolean isGroupExist = groupRepository.findById(groupId) != null;
		boolean isCourseExist = courseRepository.findById(course.getKey()) != null;

		if (isGroupExist && isCourseExist && isCourseNotInGroup) {
			Optional<Group> group = groupRepository.findById(groupId);
			group.get().addCourse(course);
			course.addGroup(group.get());
			courseRepository.save(course);

		} else {

			System.out.println("Some problems");
		}

		int updated = entityManager.createNativeQuery(SQL_ADD_COURSE_TO_GROUP).setParameter("groupId", groupId)
				.setParameter("courseKey", course.getKey()).executeUpdate();

		return updated > 0;
	}

	public boolean delete(Course course) {

		LOGGER.debug("Course deleting - " + course.toString());
		boolean deleted = courseRepository.deleteCourse(course);
		LOGGER.info("Course successfully deleted with id - " + course.getKey());

		return deleted;
	}

	public boolean deleteCourseFromGroup(Course course, long groupId) {

		boolean isCourseInGroup = group.getCourses().contains(course);
		boolean isGroupExist = groupRepository.findById(groupId) != null;
		boolean isCourseExist = courseRepository.findById(course.getKey()) != null;

		if (isGroupExist && isCourseExist && isCourseInGroup) {
			Optional<Group> group = groupRepository.findById(groupId);
			group.get().deleteCourse(course);
			course.deleteGroup(group);
			courseRepository.save(course);

		} else {

			System.out.println("Some problems");
		}

		int deleted = entityManager.createNativeQuery(SQL_DELETE_COURSE_FROM_GROUP).setParameter("groupId", groupId)
				.executeUpdate();

		return deleted > 0;
	}

	public boolean update(Course course) {

		LOGGER.debug("Course updating - " + course.toString());
		try {
			courseRepository.save(course);
			LOGGER.info("Course was successfully updated with id - " + course.getKey());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Course> findByTitle(String title) {

		LOGGER.debug("Courses finding by title");
		List<Course> coursesList = courseRepository.findByTitle(title);
		LOGGER.info("Courses were successfully found by title - " + title);

		return coursesList;
	}

	public Optional<Course> findById(long key) {

		LOGGER.debug("Course finding - " + key);
		Optional<Course> course = courseRepository.findById(key);
		LOGGER.info("Course was successfully found by id - " + key);

		return course;
	}

	public List<Course> findAll() {

		LOGGER.debug("All courses finding...");
		List<Course> coursesList = courseRepository.findAll();
		LOGGER.info("All courses were successfully found");

		return coursesList;
	}
}
