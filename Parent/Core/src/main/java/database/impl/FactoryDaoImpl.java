package database.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import database.dao.CurrencyDao;
import database.dao.FactoryDao;
import database.dao.OrderDao;
import database.dao.UserDao;
import database.dao.WalletDao;

public class FactoryDaoImpl implements FactoryDao { 
	
	private String user = "root";
	private String password = "root";
	private String url = "jdbc:mysql://localhost/birzha";
	private String driver = "com.mysql.jdbc.Driver";
	private static final Logger logger=Logger.getLogger(FactoryDaoImpl.class);
	
	public FactoryDaoImpl() {
		try { 
			Class.forName(driver);
			} catch (ClassNotFoundException e) { 
				logger.error(e.getMessage(),e);
				} 
		}
	
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
	

	public CurrencyDao getCurrencyDao() {
		return new CurrencyDaoImpl();
	}

	public WalletDao getWalletDao() {
		return new WalletDaoImpl();
	}

	public OrderDao getOrderDao() {
		return new OrderDaoImpl();
	} 
	
	
}