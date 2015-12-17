package database.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import database.dao.FactoryDao;
import database.dao.UserDao;
import database.model.User;

public class UserDaoImpl implements UserDao { 
		
		private Connection connection = null; 
		private FactoryDao factory = new FactoryDaoImpl();
		private static Logger logger=Logger.getLogger(UserDaoImpl.class); 
		


		public boolean create(User user) {
			
			PreparedStatement pst=null;
			//id,nickname,email,password, regdate, phone, finpassword, status
			String query = "insert users values(0,?,?,?,default,?,default,default)";
			try {
				connection = factory.getConnection();
				pst = connection.prepareStatement(query);
				pst.setString(1, user.getNickname());
				pst.setString(2, user.getEmail());
				pst.setString(3, user.getPassword());
				pst.setInt(4, user.getPhone());
				pst.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			finally {
				try {
					if(pst!=null) {
						pst.close();
					}					
					factory.closeConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} 
		
			
		
		public int auth(String email, String password) {
			/*return value 1 - login and password are ok
			  return value -1 - login is wrong
			  return value 0 - login is right, password is wrong
			 */
			
			String query = "SELECT password FROM Users WHERE email = ?;"; 
			PreparedStatement pst=null;
			try {
				connection = factory.getConnection();
				pst = connection.prepareStatement(query);
				pst.setString(1, email); 
				ResultSet rs = pst.executeQuery(); 
				if (rs.next()) {
					String passDB = rs.getString("password");
						if(password.equals(passDB)) {
							return 1;
						}
						else {
							return 0;
						}
				}
				else {
					return -1;
				}
							
			} catch (SQLException e) {
				logger.error("Auth query wasn't run");
				e.printStackTrace();
				return -1;
			} 
			
			finally {
				try {
					if (pst != null) {
						pst.close();
					}
					factory.closeConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}



		public boolean ifNicknameIsUnique(String nickname) {
			PreparedStatement pst=null;
			String query = "SELECT id FROM users WHERE nickname = ?;";
			try {
				connection = factory.getConnection();
				pst = connection.prepareStatement(query);
				pst.setString(1, nickname); 
				ResultSet rs = pst.executeQuery(); 
				if (rs.next()) {
						return false;
				}
				
				else {
					return true;
				}
							
			} catch (SQLException e) {
				logger.error("ifNicknameIsUnique query wasn't run");
				e.printStackTrace();
				return false;
			} 
			
			finally {
				try {
					if (pst != null) {
						pst.close();
					}
					factory.closeConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}



		public boolean ifPhoneIsUnique(int phone) {
			PreparedStatement pst=null;
			String query = "SELECT id FROM users WHERE phone = ?;";
			try {
				connection = factory.getConnection();
				pst = connection.prepareStatement(query);
				pst.setInt(1, phone); 
				ResultSet rs = pst.executeQuery(); 
				if (rs.next()) {
						return false;
				}
				
				else {
					return true;
				}
							
			} catch (SQLException e) {
				logger.error("ifPhoneIsUnique query wasn't run");
				e.printStackTrace();
				return false;
			} 
			
			finally {
				try {
					if (pst != null) {
						pst.close();
					}
					factory.closeConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}



		public boolean ifEmailIsUnique(String email) {
			PreparedStatement pst=null;
			String query = "SELECT id FROM users WHERE email = ?;";
			try {
				connection = factory.getConnection();
				pst = connection.prepareStatement(query);
				pst.setString(1, email); 
				ResultSet rs = pst.executeQuery(); 
				if (rs.next()) {
						return false;
				}
				
				else {
					return true;
				}
							
			} catch (SQLException e) {
				logger.error("ifEmailIsUnique query wasn't run");
				e.printStackTrace();
				return false;
			} 
			
			finally {
				try {
					if (pst != null) {
						pst.close();
					}
					factory.closeConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		} 
		
		
		public String getPasswordByEmail(String email) {
			PreparedStatement pst=null;
			String query = "SELECT password FROM users WHERE email = ?;";
			try {
				connection = factory.getConnection();
				pst = connection.prepareStatement(query);
				pst.setString(1, email); 
				ResultSet rs = pst.executeQuery(); 
				if (rs.next()) {
					String passDB = rs.getString("password");
					return passDB;
				}
				
				else {
					return null;
				}
							
			} catch (SQLException e) {
				logger.error("ifEmailIsUnique query wasn't run");
				e.printStackTrace();
				return null;
			} 
			
			finally {
				try {
					if (pst != null) {
						pst.close();
					}
					factory.closeConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		} 
		
		

		/*@Override
		public String getBriefInfo(int id) {
			String query = "SELECT name, surname FROM Users WHERE id = ?;"; 
			PreparedStatement stm=null;
			try {
				connection = factory.getConnection();
				stm = connection.prepareStatement(query);
				stm.setInt(1, id); 
				ResultSet rs = stm.executeQuery(); 
				if (rs.next()) {
					System.out.println("User from DB: "+rs.getString("name")+" "+rs.getString("surname"));
					return rs.getString("name")+" "+rs.getString("surname");
						
				}
				else {
					return null;
				}
							
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} 
			
			finally {
				try {
					if (stm != null) {
						stm.close();
					}
					factory.closeConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}

	*/
}

