package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import org.springframework.context.annotation.Primary;

@Primary
public interface Command {

	void execute();

	String getCommandName();

}