package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.util.ArrayList;
import java.util.List;

public class Group extends Entity<Long> {

	private String title;
	private List<Student> students;
	private List<Course> courses;

	public Group(long id, String title) {

		super(id);
		this.title = title;
		students = new ArrayList<>();
		courses = new ArrayList<>();
	}

	public Group() {

	}

	public void addCourse(Course course) {

		for (int i = 0; i < courses.size(); i++) {

			if (!courses.contains(course)) {
				this.courses.add(course);
			} else {

				System.out.println("You already added have this course in group");
			}
		}
	}

	public void deleteCourse(Course course) {
		courses.remove(course);
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void deleteStudent(Student student) {
		this.students.remove(student);
	}

	public List<Student> getStudents() {
		return students;
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

	public int getStudentsCounter() {
		return students.size();
	}

	public String toString() {

		return "Title" + title;
	}
}
