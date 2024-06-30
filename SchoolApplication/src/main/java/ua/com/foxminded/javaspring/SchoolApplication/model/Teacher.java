package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "teacher")
public class Teacher extends User implements Serializable {

	private static final long serialVersionUID = 3335155961633998707L;

	private List<Course> courses = new ArrayList<>();;

	public List<Course> getCourses() {
		return courses;
	}
}
