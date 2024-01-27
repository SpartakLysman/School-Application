package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.util.ArrayList;
import java.util.List;

public class Group extends Entity<Long> {

	private String title;
	private List<Course> courses = new ArrayList<>();;

	public Group(long id, String title) {

		super(id);
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

//	public int getStudentsCounter() {
//		return students.size();
//	}

	public String toString() {

		return "Title" + title;
	}
}
