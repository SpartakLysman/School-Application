package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

public class Student extends User implements Serializable {

	@Column(name = "group_id")
	private long group_id;

	private final int maxCourses = 4;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "students_courses", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();

	private static final long serialVersionUID = -7353839263354063173L;

	public Student(long id, long group_id, String name, String surname, String login, String passsword) {

		super(id, name, surname, login, passsword);
		this.setGroupId(group_id);

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

		if (courses.contains(course)) {
			this.courses.remove(course);
			course.deleteStudent(this);
		} else {
			System.out.println("The course not faund");
		}
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
