package ua.com.foxminded.javaspring.SchoolApplication.util;

import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import ua.com.foxminded.javaspring.SchoolApplication.command.commands.Command;
import ua.com.foxminded.javaspring.SchoolApplication.command.commands.CommandConfig;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan("ua.com.foxminded")
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

		String selectedAction = "";
		while (!"exit".equalsIgnoreCase(selectedAction)) {
			printMenu(commandMap);

			Scanner scanner = new Scanner(System.in);
			selectedAction = scanner.nextLine().trim();

			System.out.println("Selected action: " + selectedAction);
			selectedAction = selectedAction.toLowerCase();
			Command command = commandMap.get(selectedAction);

			if (command != null) {
				command.execute();
			} else {
				System.out.println("Invalid action. Please choose a valid action.");
			}
		}
	}
}
//		Map<String, Command> commandMap = commandConfig.commandMap();
//
//		String selectedAction = "";
//		while (!"exit".equals(selectedAction)) {
//			printMenu(commandMap);
//
//			Scanner scanner = new Scanner(System.in);
//			selectedAction = scanner.nextLine();
//			Command command = commandMap.get(selectedAction);
//
//			command.execute();
//		}
