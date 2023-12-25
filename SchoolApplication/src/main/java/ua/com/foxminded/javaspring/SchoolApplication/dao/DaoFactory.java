package ua.com.foxminded.javaspring.SchoolApplication.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoFactory {

	private static DaoFactory daoFactory = new DaoFactory();
	private Connection connection;

	public DaoFactory() {
		try {
			connection = DriverManager.getConnection("jdbc:postgres://localhost:5432/app", "postgre", "spartak");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {

		return connection;

	}

	public static DaoFactory getDAOFactory() {

		return daoFactory;

	}

	public void close() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
