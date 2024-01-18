package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

public class RemoveStudentByIdCommand implements Command {

	public static final String COMMAND_NAME = "RemoveStudentsById";

	private final StudentService studentService;
	private final Scanner scanner = new Scanner(System.in);

	public RemoveStudentByIdCommand(StudentService studentService) {

		this.studentService = studentService;
	}

	@Override
	public void execute() {

		List<Student> students = studentService.findAll();
		System.out.println("Enter id: ");
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