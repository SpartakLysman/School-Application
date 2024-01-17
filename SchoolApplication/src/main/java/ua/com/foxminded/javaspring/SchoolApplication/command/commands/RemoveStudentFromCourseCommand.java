package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.List;

import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

public class RemoveStudentFromCourseCommand implements Command {

	public RemoveStudentFromCourseCommand() {

	}

	@Override
	public void execute() {

	}

	@Override
	public List<Group> getGroupsWithLessOrEqualStudentCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudentsRelatedToCourse(String courseTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteStudentFromCourse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDtudentByID() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createStudent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addStudentToCourse(List<Course> coursesList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exit() {
		// TODO Auto-generated method stub
		return false;
	}
}
