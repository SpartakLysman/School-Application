package ua.com.foxminded.javaspring.SchoolApplication.command.commands;

import org.springframework.stereotype.Service;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.CourseService;
import ua.com.foxminded.javaspring.SchoolApplication.db.service.StudentService;
import ua.com.foxminded.javaspring.SchoolApplication.model.Entity;
import ua.com.foxminded.javaspring.SchoolApplication.model.Student;

import java.util.List;
import java.util.Scanner;

@Service
public class AddStudentToCourseCommand implements Command {

    public static final String COMMAND_NAME = "addStudentToCourse";

    private final CourseService courseService;
    private final StudentService studentService;
    private final Scanner scanner = new Scanner(System.in);

    public AddStudentToCourseCommand(CourseService courseService,
                                     StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        List<Student> students = studentService.findAll();
        System.out.println(students);
        // TODO написати логіку для додання студента в курс
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

}
