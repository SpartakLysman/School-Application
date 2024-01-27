package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.util.ArrayList;
import java.util.List;

public class Course extends Entity<Long> {

	private String title;
	private String describtion;
	private List<Student> students = new ArrayList<>();
	private List<Group> groups = new ArrayList<>();

	public Course(String title, String describtion) {
		this.title = title;
		this.describtion = describtion;

	}

	public Course(Long id, String title, String describtion) {
		super(id);
		this.title = title;
		this.describtion = describtion;

	}

	public Course() {

	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void deleteStudent(Student student) {
		this.students.remove(student);
	}

	public void addGroup(Group group) {
		this.groups.add(group);
	}

	public void deleteGroup(Group group) {
		this.groups.remove(group);
	}

	public void setCapacity(int capacity) {
		//
	}

	public List<Student> getStudents() {
		return students;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public String getTitle() {
		return title;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setDescribtion(String newDescribtion) {
		describtion = newDescribtion;
	}

	@Override
	public String toString() {
		return "Course info: " + " \nTitle: " + title + ",  Describtion: " + describtion + ", " + "\n"
				+ "Number Of Students: " + students.size();
	}
}
