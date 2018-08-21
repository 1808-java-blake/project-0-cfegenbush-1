package main.java.com.revature.screens;

import java.util.Scanner;

import main.java.com.revature.beans.User;
import main.java.com.revature.daos.UserDao;
import main.java.com.revature.util.AppState;

public class RegisterUserScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AppState state = AppState.state;

	@Override
	public Screen start() {
		/*
		 * creates a user and stores in a .txt file
		 */
		System.out.println(" ");
		System.out.println("***************************************************");
		System.out.println("*               REGISTER NEW USER                 *");
		System.out.println("***************************************************");
		System.out.println(" ");
		User u = new User();
		System.out.print(" Enter new username:   ");
		u.setUsername(scan.nextLine());
		System.out.print(" Enter password:       ");
		u.setPassword(scan.nextLine());
		System.out.print(" Enter first name:     ");
		u.setFirstName(scan.nextLine());
		System.out.print(" Enter last name:      ");
		u.setLastName(scan.nextLine());

		ud.createUser(u);
		state.setCurrentUser(u);

		return new LoginScreen();
	}

}
