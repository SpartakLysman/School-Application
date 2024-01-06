package ua.com.foxminded.javaspring.SchoolApplication.model;

public class Group extends Entity<Long> {
	private String title;

	public Group(long id, String title) {

		super(id);
		this.title = title;

	}

	public Group() {

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
