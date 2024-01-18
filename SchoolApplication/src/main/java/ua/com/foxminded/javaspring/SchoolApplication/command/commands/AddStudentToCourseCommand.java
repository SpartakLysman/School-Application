package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.service.CourseService;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Course;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@Service
public class AddStudentToCourseCommand implements Command {

	public static final String COMMAND_NAME = "addStudentToCourse";

	private final CourseService courseService;
	private final StudentService studentService;
	private final Scanner scanner = new Scanner(System.in);

	public AddStudentToCourseCommand(CourseService courseService, StudentService studentService) {
		this.courseService = courseService;
		this.studentService = studentService;
	}

	@Override
	public void execute() {

		List<Student> students = studentService.findAll();
		System.out.println("Enter the id for your student: ");
		students.forEach(a -> System.out.println(a));
		long studentId = scanner.nextLong();

		List<Course> courses = courseService.findAll();
		System.out.println("Enter the id for your course: ");
		courses.forEach(a -> System.out.println(a));
		long courseId = scanner.nextLong();

		// ... .
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}