package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@Service
public class GetStudentsByNameCommand implements Command {

	public static final String COMMAND_NAME = "get_students_by_name";

	private final StudentService studentService;
	private final Scanner scanner = new Scanner(System.in);

	public GetStudentsByNameCommand(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public void execute() {

		System.out.println("Enter name: ");

		String name = scanner.next();
		List<Student> studentsWithName = studentService.findByName(name);

		studentsWithName.forEach((a) -> System.out.println(a.getName() + ", " + a.getKey()));

	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}
