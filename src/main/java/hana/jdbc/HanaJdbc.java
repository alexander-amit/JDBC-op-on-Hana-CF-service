package hana.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;

import hana.model.SuperHeros;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class HanaJdbc {

	@Inject
	private DataSource dataSource;
	
	
	private static final String TABLE_NAME = "\"hana.model::SuperHeros\"";

	public String save(String id, String name) {
		String query = String.format("INSERT INTO %s (\"id\", \"name\") VALUES (?, ?)", TABLE_NAME);
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, id);
			statement.setString(2, name);
			statement.execute();
			return "Id and name saved successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error while saving data......please check cf logs";
		}
	}

	public String getName(String id) {

		String query = String.format("SELECT \"name\" FROM %s WHERE \"id\"=?", TABLE_NAME);
		if(dataSource == null) System.out.println("nulllllllllllllllllllllllllllllllllllllllllllll");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, id);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					return result.getString(1);
				}
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error while getting data......please check cf logs";
		}
	}

	@PostConstruct
	public void createTable() {
		String queryCreate = String.format("CREATE TABLE %s (\"id\" VARCHAR(20) PRIMARY KEY, \"name\" VARCHAR(20));",
				TABLE_NAME);
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(queryCreate)) {
			statement.execute();
		} catch (SQLException e) {
			// ignore, HANA does not support 'CREATE TABLE IF NOT EXIST'
		}
	}

}