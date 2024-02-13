package ua.com.foxminded.javaspring.SchoolApplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Entity
@Table(name = "courses", schema = "application")
@AttributeOverride(name = "key", column = @Column(name = "course_id"))
public class Course extends Entity<Long> implements Serializable {

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	private List<Group> groups = new ArrayList<>();

	@ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	private List<Student> students = new ArrayList<>();

	private static final long serialVersionUID = -7353139263354063173L;

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
}