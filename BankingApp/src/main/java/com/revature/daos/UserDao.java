package main.java.com.revature.daos;

import main.java.com.revature.beans.User;

public interface UserDao {
	
	public static final UserDao currentUserDao = new UserSerializer();
	
	void createUser(User u);
	User findByUsernameAndPassword(String username, String password);
	void updateUser(User u);
	void deleteUser(User u);
	
	
	

}
