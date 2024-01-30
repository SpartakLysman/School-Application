package ua.com.foxminded.javaspring.SchoolApplication.model;

import jakarta.persistence.Column;

public abstract class User extends Entity<Long> {

	@Column(name = "group_id")
	private long group_id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String passsword;

	public User(Long id, long grouop_id, String name, String surname, String login, String passsword) {

		super(id);
		this.group_id = grouop_id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.passsword = passsword;

	}

	public User(long grouop_id, String name, String surname, String login, String passsword) {

		this.group_id = grouop_id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.passsword = passsword;

	}

	public User(String name, String surname, String login, String passsword) {

		this.name = name;
		this.surname = surname;
		this.login = login;
		this.passsword = passsword;

	}

	public User() {

	}

	public long getGroupId() {
		return group_id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return passsword;
	}

	public void setGroupId(long newGroupId) {
		this.group_id = newGroupId;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setSurname(String newSurname) {
		this.surname = newSurname;
	}

	public void setLogin(String newLogin) {
		this.login = newLogin;
	}

	public void setPassword(String newPassword) {
		this.passsword = newPassword;
	}

	public String toString() {
		return "Name: " + name + ",  Surname: " + surname + ",  Login: " + login + ",  Password: " + passsword;
	}
}
