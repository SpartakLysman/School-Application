package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Entity
@Table(name = "students", schema = "application")
@AttributeOverride(name = "key", column = @Column(name = "students_id"))
public class Student extends User implements Serializable {

	@Column(name = "group_id")
	private long group_id;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "students_courses", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();

	private static final long serialVersionUID = -7353839263354063175L;

	public void addCourse(Course course) {

		if (courses.size() < 4) {
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

}