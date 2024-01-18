package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

public interface Command {

	void execute();

	List<Group> getGroupsWithLessOrEqualStudentCount();

	List<Student> getStudentsRelatedToCourse(String courseTitle);

	boolean deleteStudentFromCourse();

	boolean deleteDtudentByID();

	boolean createStudent();

	boolean addStudentToCourse(List<Course> coursesList);

	boolean exit();

}