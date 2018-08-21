package main.java.com.revature.daos;

import java.util.List;

import main.java.com.revature.beans.User;

public interface UserDao {

	public static final UserDao currentUserDao = new UserDaoJdbc();

	/*
	 * Takes in a user object and will persist that user
	 * 
	 * @param u
	 */

	void createUser(User u);

	User findByUsernameAndPassword(String username, String password); // throws User does not exist exception

	void updateUser(User u, String currentUsername);

	void deleteUser(User u);

	String verifyUserExistsForAccountSharing(String username);

	List<Integer> getUserAccounts(String username);
	
	List<String> getAllUsernames();

}
