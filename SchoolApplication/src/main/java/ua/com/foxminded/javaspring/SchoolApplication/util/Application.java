package ua.com.foxminded.javaspring.SchoolApplication.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	public String hello() {
//		return "Hello World";
//	}

}
