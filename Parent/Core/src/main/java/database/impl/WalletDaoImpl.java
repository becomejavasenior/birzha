package database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import database.dao.FactoryDao;
import database.dao.WalletDao;

public class WalletDaoImpl implements WalletDao {

	private Connection connection = null; 
	private FactoryDao factory = new FactoryDaoImpl();
	private static final Logger logger=Logger.getLogger(WalletDaoImpl.class); 
	
	public int getWalletBalance(String email) {
		PreparedStatement pst1=null;
		PreparedStatement pst2=null;
		int balanceByOrders = 0;
		int balanceByCompAccount = 0;
		String query1 = 
				"SELECT SUM(CASE WHEN wallet_id2 IS NOT NULL THEN amount2 ELSE 0 END)-SUM(amount1) as 'balanceByOrders' FROM orders "
				+ "WHERE wallet_id1=(select id from wallets where user_id=(select id from users where email=?));";
		String query2 = 
				"SELECT SUM(plus)-SUM(minus) as 'balanceByCompAccount' FROM company_account "
				+ "WHERE wallet_id1=(select id from wallets where user_id=(select id from users where email=?));";
		try {
			connection = factory.getConnection();
			pst1 = connection.prepareStatement(query1);
			pst1.setString(1, email); 
			ResultSet rs1 = pst1.executeQuery(); 
			if (rs1.next()) {
				balanceByOrders = rs1.getInt("balanceByOrders");
			}
			
			pst2 = connection.prepareStatement(query2);
			pst2.setString(1, email); 
			ResultSet rs2 = pst2.executeQuery(); 
			if (rs2.next()) {
				balanceByCompAccount = rs2.getInt("balanceByCompAccount");
			}
			
			return balanceByCompAccount-balanceByOrders;
						
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			return 0;
		} 
		
		finally {
			try {
				if (pst1 != null) {
					pst1.close();
				}
				if (pst2 != null) {
					pst2.close();
				}
				factory.closeConnection(connection);
			} catch (SQLException e) {
				logger.error(e.getMessage(),e);
			}
			
		}
		
	}

}
