package database.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import database.dao.FactoryDao;
import database.dao.UserDao;

public class FactoryDaoImpl implements FactoryDao { 
	
	private String user = "root";
	private String password = "root";
	private String url = "jdbc:mysql://localhost/birzha";
	private String driver = "com.mysql.jdbc.Driver";
	
	public Connection getConnection() throws SQLException { 
		return DriverManager.getConnection(url, user, password);
	} 
	
	public void closeConnection(Connection connection)  throws SQLException{
         if (connection != null){
               connection.close();
         }
	}

	public UserDao getUserDao() { 
		return new UserDaoImpl(); 
	} 
	
		
	public FactoryDaoImpl() {
		try { 
			Class.forName(driver);
			} catch (ClassNotFoundException e) { 
				System.out.println("Problem with driver");
				e.printStackTrace(); 
				} 
		} 
}