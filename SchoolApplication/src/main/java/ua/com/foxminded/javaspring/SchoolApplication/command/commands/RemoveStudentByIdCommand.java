package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.service.CourseService;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@Service
@Qualifier("fifth")

public class RemoveStudentByIdCommand implements Command {

	public static final String COMMAND_NAME = "RemoveStudentById";

	private final CourseService courseService;
	private final StudentService studentService;
	private final Scanner scanner = new Scanner(System.in);

	public RemoveStudentByIdCommand(StudentService studentService, CourseService courseService) {

		this.courseService = courseService;
		this.studentService = studentService;
	}

	@Override
	public void execute() {

		List<Student> students = studentService.findAll();

		System.out.println("Enter id for student: ");
		students.forEach((a) -> System.out.println(a));

		int id = scanner.nextInt();

		Student student = studentService.findById(id);
		students.remove(student);

		System.out.println(student + " was removed");
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}
