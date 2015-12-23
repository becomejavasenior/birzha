package database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import database.dao.CurrencyDao;
import database.dao.FactoryDao;
import database.model.Currency;

public class CurrencyDaoImpl implements CurrencyDao{

	private Connection connection = null; 
	private FactoryDao factory = new FactoryDaoImpl();
	private static final Logger logger=Logger.getLogger(CurrencyDaoImpl.class); 
	
	public ArrayList<Currency> getCurrList() {
		
		Statement stm=null;
		ArrayList<Currency> curr = new ArrayList<Currency>();
		try {
			connection = factory.getConnection();
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT id, name, rate FROM currencies;"); 
			int i = 0;
			while (rs.next()) {
				int id = rs.getInt("id");
				curr.get(i).setId(id);
				String name = rs.getString("name");
				curr.get(i).setName(name);
				int rate = rs.getInt("rate");
				curr.get(i).setId(rate);
			}
			return curr;
						
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			return null;
		} 
		
		finally {
			try {
				if (stm != null) {
					stm.close();
				}
				factory.closeConnection(connection);
			} catch (SQLException e) {
				logger.error(e.getMessage(),e);
			}
			
		}
		
	}

}
