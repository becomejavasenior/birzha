package database.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface FactoryDao {

	public Connection getConnection() throws SQLException;

	public void closeConnection(Connection connection)  throws SQLException;
	
	public UserDao getUserDao(); 
	
}
