package database.dao;

import database.model.User;

public interface UserDao {
	
	public boolean create(User user);

	//public String getBriefInfo(int login);
	
	public int auth(String email, String password);
	
	public boolean ifNicknameIsUnique(String nickname);
	
	public boolean ifPhoneIsUnique(int phone);
	
	public boolean ifEmailIsUnique(String email);
	
	public String getPasswordByEmail(String email);
	
	//public String getAuthStatus(int id);
	
	//public void update(User user); 
	
	//public void delete(User user); 

}
