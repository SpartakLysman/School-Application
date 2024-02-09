package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "groups", schema = "application")
@AttributeOverride(name = "key", column = @Column(name = "group_id"))
public class Group extends Entity<Long> implements Serializable {

	@Column(name = "title")
	private String title;

	private List<Course> courses = new ArrayList<>();

	private static final long serialVersionUID = -7353719263354063173L;

	public Group(long key, String title) {

		super(key);
		this.title = title;

	}

	public Group() {

	}

	public void addCourse(Course course) {

		if (!courses.contains(course)) {
			this.courses.add(course);
		} else {

			System.out.println("You already added have this course in group");
		}
	}

	public void deleteCourse(Course course) {

		if (courses.contains(course)) {
			courses.remove(course);
		} else {

			System.out.println("The course not found");
		}
	}

	public List<Course> getCourses() {
		return courses;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public String toString() {

		return "Title" + title;
	}
}
