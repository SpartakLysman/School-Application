package ua.com.foxminded.javaspring.SchoolApplication.model;

public abstract class User extends Entity<Long> {

	private String name;
	private String surname;
	private String login;
	private String passsword;

	public User(Long id, String name, String surname, String login, String passsword) {

		super(id);
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
