package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.javaspring.SchoolApplication.db.service.CourseService;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

public class RemoveStudentFromCourseCommand implements Command {

	public static final String COMMAND_NAME = "RemoveStudentFromCourse";

	private final CourseService courseService;
	private final StudentService studentService;
	private final Scanner scanner = new Scanner(System.in);

	public RemoveStudentFromCourseCommand(CourseService courseService, StudentService studentService) {
		this.courseService = courseService;
		this.studentService = studentService;
	}

	@Override
	public void execute() {

		List<Student> students = studentService.findAll();
		System.out.println("Enter the id for student: ");
		students.forEach(a -> System.out.println(a));

		long studentId = scanner.nextLong();
//... .
		System.out.println("Student was removed");
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}
