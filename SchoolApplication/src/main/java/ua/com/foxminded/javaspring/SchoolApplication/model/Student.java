package ua.com.foxminded.javaspring.SchoolApplication.model;

public class Student extends User {

	private long group_id;

	public Student(long id, long group_id, String name, String surname, String login, String passsword) {

		super(id, name, surname, login, passsword);
		this.setGroupId(group_id);

	}

	public Student(long group_id, String name, String surname, String login, String passsword) {

		super(group_id, name, surname, login, passsword);
		this.setGroupId(group_id);

	}

	public Student(String name, String surname, String login, String passsword) {

		super(name, surname, login, passsword);

	}

	public Student() {

	}

	public long getGroupId() {
		return group_id;
	}

	public void setGroupId(long group_id) {
		this.group_id = group_id;
	}
}
