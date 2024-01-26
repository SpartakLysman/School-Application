package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfig {

	@Autowired
	private List<Command> commands;

//	public CommandConfig(List<Command> commands) {
//		this.commands = commands;
//	}

	@Bean
	public Map<String, Command> commandMap() {
		return commands.stream()
				.collect(Collectors.toMap(command -> command.getCommandName() + "\n", command -> command));
	}
}
