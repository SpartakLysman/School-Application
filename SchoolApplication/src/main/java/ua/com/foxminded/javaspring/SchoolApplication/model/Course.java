package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "courses", schema = "application")
@AttributeOverride(name = "key", column = @Column(name = "course_id"))
public class Course extends Entity<Long> implements Serializable {

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "students_courses", schema = "application", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students = new ArrayList<>();

	@OneToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	private List<Group> groups = new ArrayList<>();

	@OneToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	private Teacher teacher;

	private static final long serialVersionUID = -7353139263354063173L;

	public Course(Long key, String title, String description, Teacher teacher) {
		super(key);
		this.title = title;
		this.description = description;
		this.teacher = teacher;
	}

	public Course(Long key, String title, String description) {
		super(key);
		this.title = title;
		this.description = description;
	}

	public Course(String title, String description) {
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

	public List<Student> getStudents() {
		return students;
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

	public List<Group> getGroups() {
		return groups;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	@Override
	public String toString() {
		return "Course info: " + " \nTitle: " + title + ",  Description: " + description + ", " + "\n"
				+ "Number Of Students: " + students.size();
	}
}