package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Connection conn = null;

	public static Connection getConn() throws SQLException {
		if (conn == null || conn.isClosed()) {
			conn = createConnection();
		}
		return conn;
	}

	private static Connection createConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/Pet_Shop";
			String user = "postgres";
			String password = "admin";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			throw new SQLException("Deu erro aqui a conexão.", e);
		}
	}
}
