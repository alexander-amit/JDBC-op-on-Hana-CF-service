package hana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hana.jdbc.HanaJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import javax.sql.DataSource;

@RestController
public class HanaJdbcOperation {

	private static final String TABLE_NAME = "\"hana.model::SuperHeros\"";

	@Inject
	private DataSource dataSource;

	@RequestMapping("/jdbc/save/{id}/{name}")
	public String save(@PathVariable("id") String id, @PathVariable("name") String name) {
		// System.out.println("break point 1");
		// return hanaJdbc.save(id, name);
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

	@RequestMapping("/jdbc/create")
	public String createTable() {
		String queryCreate = String.format("CREATE TABLE %s (\"id\" VARCHAR(20) PRIMARY KEY, \"name\" VARCHAR(20));",
				TABLE_NAME);
		if (dataSource == null)
			System.out.println("nulllllllllllllllllllllllllllllllllllllllllllll");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(queryCreate)) {
			statement.execute();
			return "Table created successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error while saving data......please check cf logs";
			// ignore, HANA does not support 'CREATE TABLE IF NOT EXIST'
		}
	}

	@RequestMapping("/jdbc/getdetails/{id}")
	public String getName(@PathVariable("id") String id) {

		String query = String.format("SELECT \"name\" FROM %s WHERE \"id\"=?", TABLE_NAME);
		if (dataSource == null)
			System.out.println("nulllllllllllllllllllllllllllllllllllllllllllll");
		System.out.println("Id entered is ---->" + id);
		try (Connection connection = dataSource.getConnection();

				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, id);
			try (ResultSet result = statement.executeQuery()) {
				System.out.println("Got the result------> now going to return result to user");
				if (result.next()) {

					return result.getString(1);
				}
				return "Got Empty set as result";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error while getting data......please check cf logs";
		}
	}

	/*
	 * public String get(@PathVariable("id") String id) {
	 * System.out.println("dsfdsfdsfdsf"); return hanaJdbc.getName(id);
	 * 
	 * }
	 * 
	 * @RequestMapping("/jdbc/createTable") public String createTable() {
	 * hanaJdbc.createTable(); return "table created successfully"; }
	 */
}