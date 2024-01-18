package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.List;
import java.util.Scanner;

import ua.com.foxminded.javaspring.SchoolApplication.db.service.GroupService;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

public class GetGroupsCommand implements Command {

	public static final String COMMAND_NAME = "GetGroupsWithLessOrEqualStudentCount";

	private final GroupService groupService;
	private final StudentService studentService;
	private final Scanner scanner = new Scanner(System.in);

	public GetGroupsCommand(GroupService groupService, StudentService studentService) {
		this.groupService = groupService;
		this.studentService = studentService;
	}

	@Override
	public void execute() {

		List<Group> groups = groupService.findAll();
		System.out.println("Enter max number of student: ");
		String number = scanner.next();

		for (int i = 0; i < groups.size(); i++) {
			// ... .
		}
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
