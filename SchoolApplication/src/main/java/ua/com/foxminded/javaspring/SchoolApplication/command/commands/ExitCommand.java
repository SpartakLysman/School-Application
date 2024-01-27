package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class ExitCommand implements Command {

	public static final String COMMAND_NAME = "exit";

	private final Scanner scanner = new Scanner(System.in);

	@Override
	public void execute() {
		System.out.println("Thank you, bye ;)");
		return;

	}

	@Override
	public String getCommandName() {

		return COMMAND_NAME;
	}

}
