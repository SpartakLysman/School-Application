package ua.com.foxminded.javaspring.SchoolApplication.db.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.impl.postgre.PostgreSqlCourseDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;
import ua.com.foxminded.javaspring.SchoolApplication.util.LoggingController;

@Service
public class CourseService {

	private PostgreSqlCourseDao courseRepository;
	private GroupService groupService;
	private Course course;
	private Group group;

	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

	@Autowired
	public CourseService() {
		this.course = new Course();
		this.group = new Group();
	}

	public boolean create(Course course) {

		LOGGER.debug("Course creating - " + course.toString());
		boolean created = courseRepository.create(course);
		LOGGER.info("Course was created successfully with id - " + course.getKey());

		return created;
	}

	public int[] createAll(List<Course> coursesList) {

		LOGGER.debug("All courses creating...");
		int[] createdAll = courseRepository.createAll(coursesList);
		LOGGER.info("All courses were successfully created - " + coursesList.toString());

		return createdAll;
	}

	public boolean addCourseToGroup(Course course, long groupId) {

		boolean isCourseNotInGroup = !group.getCourses().contains(course);
		boolean isGroupExist = groupService.findById(groupId) != null;
		boolean isCourseExist = courseRepository.findById(course.getKey()) != null;
		boolean isCourseAddedToGroup = false;

		if (isGroupExist && isCourseExist && isCourseNotInGroup) {
			Group group = groupService.findById(groupId);
			group.addCourse(course);

			courseRepository.update(course);
			isCourseAddedToGroup = true;
		}

		return isCourseAddedToGroup;
	}

	public boolean deleteCourseFromGroup(Course course, long groupId) {

		boolean isCourseNotInGroup = !group.getCourses().contains(course);
		boolean isGroupExist = groupService.findById(groupId) != null;
		boolean isCourseExist = courseRepository.findById(course.getKey()) != null;
		boolean isCourseDeletedFromGroup = false;

		if (isGroupExist && isCourseExist && isCourseNotInGroup) {
			Group group = groupService.findById(groupId);
			group.deleteCourse(course);
			courseRepository.update(course);
			isCourseDeletedFromGroup = true;
		}

		return isCourseDeletedFromGroup;
	}

	public List<Course> findAll() {

		LOGGER.debug("All courses finding...");
		List<Course> coursesList = courseRepository.findAll();
		LOGGER.info("All courses were successfully found");

		return coursesList;
	}

	public Course findById(long key) {

		LOGGER.debug("Course finding - " + key);
		Course course = courseRepository.findById(key);
		LOGGER.info("Course was successfully found by id - " + key);

		return course;

	}

	public List<Course> findByTitle(String title) {

		LOGGER.debug("Courses finding by title");
		List<Course> coursesList = courseRepository.findByTitle(title);
		LOGGER.info("Courses were successfully found by title - " + title);

		return coursesList;
	}

	public boolean delete(Course course) {

		LOGGER.debug("Course deleting - " + course.toString());
		boolean deleted = courseRepository.delete(course);
		LOGGER.info("Course successfully deleted with id - " + course.getKey());

		return deleted;
	}

	public boolean update(Course course) {

		LOGGER.debug("Course updating - " + course.toString());
		boolean updated = courseRepository.update(course);
		LOGGER.info("Course was successfully updated with id - " + course.getKey());

		return updated;
	}
}
