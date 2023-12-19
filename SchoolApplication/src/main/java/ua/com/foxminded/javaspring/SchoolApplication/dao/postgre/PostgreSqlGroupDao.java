package ua.com.foxminded.javaspring.SchoolApplication.dao.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import ua.com.foxminded.javaspring.SchoolApplication.dao.GroupDao;
import ua.com.foxminded.javaspring.SchoolApplication.model.Group;

public class PostgreSqlGroupDao implements GroupDao {

	private DataSource dataSource;

	public PostgreSqlGroupDao(DataSource dataSource) {

		this.dataSource = dataSource;

	}

	public Group create(Group group) {
		String sql = " insert into application.groups (group_name) " + " values (?)";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, group.getName());

			int update = preparedStatement.executeUpdate();

			if (update == 0) {
				throw new SQLException();
			}

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long key = resultSet.getLong(1);
				group.setKey(key);
				return group;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Group update(Group group) {
		String sql = "update groups set name = ? " + " where groups.group_id = " + group.getKey();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedSt1 = connection.prepareStatement(sql)) {

			preparedSt1.setString(1, group.getName());

			int update = preparedSt1.executeUpdate();

			if (update == 0) {
				throw new SQLException();
			}

			return group;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Group delete(Group group) {
		String sql = "delete from groups " + " where groups.group_id =  " + group.getKey();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			int resultSet = preparedStatement.executeUpdate();

			if (resultSet == 0) {
				throw new SQLException();
			}

			return group;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Group> findAll(long id) {

		List<Group> groupsList = new ArrayList<>();

		String sql = " select groups * " + " from application.groups " + " where groups.group_id = ? ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setLong(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long group_id = resultSet.getLong(1);
				String groupName = resultSet.getString(2);

				groupsList.add(new Group(group_id, groupName));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return groupsList;
	}

	@Override
	public List<Group> findAll() {
		List<Group> groupsList = new ArrayList<>();

		String sql = "select groups * " + " from application.groups ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long groups_id = resultSet.getLong(1);
				String groupName = resultSet.getString(2);

				groupsList.add(new Group(groups_id, groupName));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return groupsList;
	}
}
