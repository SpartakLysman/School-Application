package ua.com.foxminded.javaspring.SchoolApplication.model;

public class Course extends Entity<Long> {

	private String title;
	private String describtion;

	public Course(Long id, String title, String describtion) {
		super(id);
		this.title = title;
		this.describtion = describtion;
	}

	public Course(String title, String describtion) {
		this.title = title;
		this.describtion = describtion;

	}

	public String getTitle() {
		return title;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setDescribtion(String newDescribtion) {
		describtion = newDescribtion;
	}

	public String toString() {
		return "Title: " + title + ",  Describtion: " + describtion;
	}
}
