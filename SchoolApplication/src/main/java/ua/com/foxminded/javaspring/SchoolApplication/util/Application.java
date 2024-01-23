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
@ComponentScan({ "ua.com.foxminded" })

public class Application implements CommandLineRunner {

	private final ApplicationContext applicationContext;

	private Command command;

	private static String[] actionsList = { "1. Find all the groups with less or equal student count" + "\n"
			+ "2. Find all students related to the course with the given name" + "\n"
			+ "3. Delete the student from one of their courses" + "\n" + "4. Delete student by ID" + "\n"
			+ "5. Add a new student" + "\n" + "6. Add a student to the course from the list" + "\n" + "7. Exit" };

	@Autowired
	public Application(ApplicationContext applicationContext, Command command) {

		this.applicationContext = applicationContext;
		this.command = command;
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

		System.out.println("Choose one of the actions: ");

		for (String action : commandMap.keySet()) {
			System.out.println(" - " + action);
		}

		System.out.print(" - exit");
	}

	private void actionProcessing(CommandConfig commandConfig) {

		Map<String, Command> commandMap = commandConfig.commandMap();

		String selectedAction = "";
		while (!"exit".equals(selectedAction)) {
			printMenu(commandMap);

			Scanner scanner = new Scanner(System.in);
			selectedAction = scanner.nextLine();
			Command command = commandMap.get(selectedAction);

			command.execute();
		}
	}
}