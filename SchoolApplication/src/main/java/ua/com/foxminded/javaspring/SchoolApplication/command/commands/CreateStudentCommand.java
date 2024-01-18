package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.Scanner;

import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

public class CreateStudentCommand implements Command {

	public static final String COMMAND_NAME = "CreateStudent";

	private final StudentService studentService;
	private final Scanner scanner = new Scanner(System.in);

	public CreateStudentCommand(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public void execute() {

		System.out.println("Enter group id: ");
		long groupId = scanner.nextLong();

		System.out.println("Enter name: ");
		String name = scanner.next();

		System.out.println("Enter surname: ");
		String surname = scanner.next();

		System.out.println("Enter login: ");
		String login = scanner.next();

		System.out.println("Enter password: ");
		String password = scanner.next();

		Student newStudent = new Student(groupId, name, surname, login, password);

		boolean create = studentService.create(newStudent);
		if (create == true) {
			System.out.println(newStudent + " was added");

		} else {

			System.out.println("Some problems!");
		}
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}
