package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "courses", schema = "application")
public class Course extends Entity<Long> implements Serializable {

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "groups", fetch = FetchType.LAZY)
	private List<Group> groups = new ArrayList<>();

	@ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
	private List<Student> students = new ArrayList<>();

	private static final long serialVersionUID = -7353839263354063173L;

	public Course(String title, String description) {
		this.title = title;
		this.description = description;

	}

	public Course(Long id, String title, String description) {
		super(id);
		this.title = title;
		this.description = description;

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

	public void deleteGroup(Group group1) {
		this.groups.remove(group1);
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

	public String getDescription() {
		return description;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setDescription(String newDescription) {
		description = newDescription;
	}

	@Override
	public String toString() {
		return "Course info: " + " \nTitle: " + title + ",  Description: " + description + ", " + "\n"
				+ "Number Of Students: " + students.size();
	}
}