package ua.com.foxminded.javaspring.SchoolApplication.model;

public class Group extends Entity<Long> {
	private String name;

	public Group(long id, String name) {

		super(id);
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public String toString() {

		return "Name: " + name;

	}
}
