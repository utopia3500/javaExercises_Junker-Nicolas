package fr.junker.myApi.datasource;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnector {
	public Connection createConnection() throws Exception{
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/philiance", "root", "root");
			return conn;
		}
		catch(Exception ex){
			throw new Exception("erreur class MySQLConnector" + ex.getMessage());
		}
	}
}

