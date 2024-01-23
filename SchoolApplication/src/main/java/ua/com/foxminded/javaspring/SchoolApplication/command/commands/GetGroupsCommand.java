package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import ua.com.foxminded.javaspring.SchoolApplication.db.service.GroupService;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

@Service
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
		int number = scanner.nextInt();

		List<Group> groupsWithLessOrEqualStudentCount = new ArrayList<>();
		for (int i = 0; i < groups.size(); i++) {
			if (groups.get(i).getStudentsCounter() < number) {
				groupsWithLessOrEqualStudentCount.add(groups.get(i));
			}
		}
		groupsWithLessOrEqualStudentCount.forEach((a) -> System.out.println(a));
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
