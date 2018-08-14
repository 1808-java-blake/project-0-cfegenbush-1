package main.java.com.revature.screens;

import java.util.Scanner;

import main.java.com.revature.beans.User;
import main.java.com.revature.daos.UserDao;

public class LoginScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	
	@Override
	public Screen start() {
		/*
		 * User can sign in or create a new user
		 * Logging in as an admin navigates to the admin screen
		 */
		System.out.print("Enter username or type Register to sign up: ");
		String username = scan.nextLine();
		if ("register".equalsIgnoreCase(username)) {
			return new RegisterUserScreen();
		}
		System.out.print("Enter password: ");
		String password = scan.next();
		
		User currentUser = ud.findByUsernameAndPassword(username, password);
		
		if (currentUser != null) {
			if ("admin".equals(currentUser.getUsername())) {
				return new AdminScreen();
			} else {
				return new HomeScreen(currentUser);
			}
		}
		
		System.out.print("Unable to login");
		return this;
	}
	
	

}
