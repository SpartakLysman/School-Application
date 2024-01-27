package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.service.CourseService;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@Service
public class RemoveStudentByIdCommand implements Command {

	public static final String COMMAND_NAME = "remove_student_by_id";

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
		students.forEach((a) -> System.out.println(a.getKey()));

		int id = scanner.nextInt();

		Student student = studentService.findById(id);
		studentService.delete(student);

		System.out.println(student.getName() + ", Id: " + student.getKey() + "-  was removed");
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}
