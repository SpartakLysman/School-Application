package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

@Service
public class CreateStudentCommand implements Command {

	public static final String COMMAND_NAME = "create_student";

	private final StudentService studentService;

	private final Scanner scanner = new Scanner(System.in);

	@Autowired
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

		Student newStudent = null;

		Optional<Student> student = studentService.findMaxId();

		long currentLargestId = 1L;

		if (student.isPresent()) {

			newStudent = new Student(((student.get().getKey()) + 1L), groupId, name, surname, login, password);

		} else {
			System.out.println("Student do not present");
		}

		boolean create = studentService.create(newStudent);
		newStudent.setKey((student.get().getKey() + 1L));
		if (create == true) {
			System.out.println(newStudent.getName() + ", Id: " + newStudent.getKey() + "-  was сreated");

		} else {

			System.out.println("Some problems!");
		}
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}
