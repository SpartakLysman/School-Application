package ua.com.foxminded.javaspring.SchoolApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import ua.com.foxminded.javaspring.SchoolApplication.util.Application;

@TestConfiguration(proxyBeanMethods = false)
public class TestSchoolApplication {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
	}

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestSchoolApplication.class).run(args);
	}

}
