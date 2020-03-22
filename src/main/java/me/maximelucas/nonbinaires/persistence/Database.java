package me.maximelucas.nonbinaires.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static Database instance;
	private Connection connection;
	private String url = "jdbc:sqlite:nbessentials.db";

	private Database() throws SQLException {
		this.connection = DriverManager.getConnection(url);
		System.out.println("Connection to database has been established.");
	}

	public Connection getConnection() {
		return connection;
	}

	public static Database getInstance() throws SQLException {
		if (instance == null) {
			instance = new Database();
		} else if (instance.getConnection().isClosed()) {
			instance = new Database();
		}

		return instance;
	}
}
