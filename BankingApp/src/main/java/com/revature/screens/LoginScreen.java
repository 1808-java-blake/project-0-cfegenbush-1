package main.java.com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import main.java.com.revature.beans.User;
import main.java.com.revature.daos.UserDao;
import main.java.com.revature.util.AppState;

public class LoginScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AppState state = AppState.state;
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		/*
		 * User can sign in or create a new user Logging in as an admin navigates to the
		 * admin screen
		 */
		log.debug("started login screen");
		System.out.println("***************************************************");
		System.out.println("*               ELECTRONIC BANKING                *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.print(" Enter username or type 'Register' to sign up:   ");
		String username = scan.nextLine();
		log.trace("username = " + username);

		if ("register".equalsIgnoreCase(username)) {
			return new RegisterUserScreen();
		}

		System.out.print(" Enter password:   ");
		String password = scan.next();

		log.debug("received users credentials");
		User currentUser = ud.findByUsernameAndPassword(username, password);

		if (currentUser != null) {
			state.setCurrentUser(currentUser);
			log.info("User logged in successfully");
			if ("admin".equals(currentUser.getUsername())) {
				return new AdminScreen();
			} else {
				log.info("Welcome" + currentUser);
				return new HomeScreen();
			}
		}

		System.out.println("Unable to login");
		return new LoginScreen();
	}

}
