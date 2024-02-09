package ua.com.foxminded.javaspring.SchoolApplication;

import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ua.com.foxminded.javaspring.SchoolApplication.command.commands.Command;
import ua.com.foxminded.javaspring.SchoolApplication.command.commands.CommandConfig;

@SpringBootApplication(scanBasePackages = { "ua.com.foxminded.javaspring.SchoolApplication" })
public class Application implements CommandLineRunner {

	private final ApplicationContext applicationContext;

	private final CommandConfig commandConfig;

	private static String[] actionsList = { "1. Find all the groups with less or equal student count" + "\n"
			+ "2. Find all students related to the course with the given name" + "\n"
			+ "3. Delete the student from one of their courses" + "\n" + "4. Delete student by ID" + "\n"
			+ "5. Add a new student" + "\n" + "6. Add a student to the course from the list" + "\n" + "7. Exit"
			+ "\n" };

	@Autowired
	public Application(ApplicationContext applicationContext, CommandConfig commandConfig) {

		this.applicationContext = applicationContext;
		this.commandConfig = commandConfig;
	}

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		CommandConfig commandConfig = applicationContext.getBean(CommandConfig.class);
		actionProcessing(commandConfig);
	}

	public void printMenu(Map<String, Command> commandMap) {

		System.out.println("Choose one of the actions: " + "\n");

		for (Map.Entry<String, Command> entry : commandMap.entrySet()) {
			System.out.println(" - " + entry.getKey());
		}

		System.out.print(" - exit" + "\n");
	}

	private void actionProcessing(CommandConfig commandConfig) {
		Map<String, Command> commandMap = commandConfig.commandMap();

		System.out.println("Available commands: " + commandMap.keySet());

		String selectedAction = "";
		while (!"exit".equalsIgnoreCase(selectedAction)) {
			printMenu(commandMap);

			Scanner scanner = new Scanner(System.in);
			selectedAction = scanner.nextLine().trim().toLowerCase();

			System.out.println("Selected action: " + selectedAction);

			if (commandMap.containsKey(selectedAction)) {
				Command command = commandMap.get(selectedAction);
				System.out.println("Executing command: " + command.getClass().getSimpleName());
				command.execute();
			} else {
				System.out.println("Invalid action. Please choose a valid action.");
				System.out.println("Available actions: " + String.join(", ", commandMap.keySet()));
			}
		}
	}
}
