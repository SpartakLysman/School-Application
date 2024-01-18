package ua.com.foxminded.javaspring.SchoolApplication.util;

import java.util.HashMap;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import ua.com.foxminded.javaspring.SchoolApplication.command.commands.AddStudentToCourseCommand;
import ua.com.foxminded.javaspring.SchoolApplication.command.commands.Command;
import ua.com.foxminded.javaspring.SchoolApplication.command.commands.CreateStudentCommand;
import ua.com.foxminded.javaspring.SchoolApplication.command.commands.GetGroupsCommand;
import ua.com.foxminded.javaspring.SchoolApplication.command.commands.GetStudentsCommand;
import ua.com.foxminded.javaspring.SchoolApplication.command.commands.RemoveStudentByIdCommand;
import ua.com.foxminded.javaspring.SchoolApplication.command.commands.RemoveStudentFromCourseCommand;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

public class Application {

	private static HashMap<String, Command> commandsMap = new HashMap<>();

	private static String[] actionsList = { "1. Find all the groups with less or equal student count" + "\n"
			+ "2. Find all students related to the course with the given name" + "\n"
			+ "3. Delete the student from one of their courses" + "\n" + "4. Delete student by ID" + "\n"
			+ "5. Add a new student" + "\n" + "6. Add a student to the course from the list" + "\n" + "7. Exit" };

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);

		printMenu(actionsList);
		actionProcessing();
	}

	public Application() {
		commandsMap.put("Find all groups with less or equal student count", new GetGroupsCommand());
		commandsMap.put("Find all students related to the course", new GetStudentsCommand());
		commandsMap.put("Delete student from one course", new RemoveStudentFromCourseCommand());
		commandsMap.put("Delete student by ID", new RemoveStudentByIdCommand());
		commandsMap.put("Add student", new CreateStudentCommand());
		commandsMap.put("Add student to course from list", new AddStudentToCourseCommand());
	}

	public static void printMenu(String[] actionsList) {

		for (String action : actionsList) {
			System.out.println(action);
		}

		System.out.print("Choose one of the actions: ");
	}

	private static void actionProcessing() throws Exception {

		Scanner scanner = new Scanner(System.in);
		int selectedAction = scanner.nextInt();

		if (selectedAction >= 1 && selectedAction <= actionsList.length) {

			if (selectedAction == 1) {
				
				System.out.print("Action number " + selectedAction + " was selected" + "\n");
				for (int i = 0; i < commandsMap.size(); i++) {
					if (commandsMap.containsKey("Find all groups with less or equal student count")) {
						commandsMap.values()
					}
				}
			}

			if (selectedAction == 2) {

				System.out.print("Action number " + selectedAction + " was selected" + "\n");

			}

			if (selectedAction == 3) {

				System.out.print("Action number " + selectedAction + " was selected" + "\n");

			}

			if (selectedAction == 4) {

				System.out.print("Action number " + selectedAction + " was selected" + "\n");

			}

			if (selectedAction == 5) {
				System.out.print("Action number " + selectedAction + " was selected" + "\n");

			}

			if (selectedAction == 6) {
				System.out.print("Action number " + selectedAction + " was selected" + "\n");

			}

			if (selectedAction == 7) {

				System.out.print("Bye ;) ");
				return;
			}

		} else {
			throw new Exception("Action number should be between 1 and " + actionsList.length + "!");
		}
	}

}
