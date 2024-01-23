package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

	private long group_id;
	private List<Course> courses;
	private final int maxCourses = 4;

	public Student(long id, long group_id, String name, String surname, String login, String passsword) {

		super(id, name, surname, login, passsword);
		this.setGroupId(group_id);
		courses = new ArrayList();

	}

	public Student(long group_id, String name, String surname, String login, String passsword) {

		super(group_id, name, surname, login, passsword);
		this.setGroupId(group_id);

	}

	public Student(long group_id, String name, String surname, String login, String passsword, List<Course> courses) {

		super(group_id, name, surname, login, passsword);
		this.setGroupId(group_id);
		courses = new ArrayList<>();

	}

	public Student(String name, String surname, String login, String passsword) {

		super(name, surname, login, passsword);

	}

	public Student() {

	}

	public void addCourse(Course course) {
		if (courses.size() < maxCourses) {
			this.courses.add(course);
			course.addStudent(this);
		} else {
			System.out.println("You already added max amount of courses");
		}
	}

	public void deleteCourse(Course course) {
		this.courses.remove(course);
		course.deleteStudent(this);
	}

	public List<Course> getCourses() {
		return courses;
	}

	public int getMaxCourses() {
		return maxCourses;
	}

	public long getGroupId() {
		return group_id;
	}

	public void setGroupId(long group_id) {
		this.group_id = group_id;
	}

	public String toString() {

		return "Group id: " + group_id;
	}
}
