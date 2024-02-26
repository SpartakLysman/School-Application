package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.GroupService;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

@Service
public class GetGroupsCommand implements Command {

	public static final String COMMAND_NAME = "get_groups_with_less_or_equal_student_count";

	private final GroupService groupService;
	private final StudentService studentService;
	private final Scanner scanner = new Scanner(System.in);

	public GetGroupsCommand(GroupService groupService, StudentService studentService) {
		this.groupService = groupService;
		this.studentService = studentService;
	}

	@Override
	@Transactional
	public void execute() {
		List<Group> groups = groupService.findAll();
		System.out.println("Enter max number of students: ");
		int number = scanner.nextInt();

		List<Group> groupsWithLessOrEqualStudentCount = new ArrayList<>();

		for (Group group : groups) {

			if (group != null && group.getCourses() != null) {
				long studentCount = group.getCourses().stream().flatMap(course -> course.getStudents().stream())
						.distinct().count();

				if (studentCount <= number) {
					groupsWithLessOrEqualStudentCount.add(group);
				}
			}
		}
		groupsWithLessOrEqualStudentCount.forEach(System.out::println);
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}
