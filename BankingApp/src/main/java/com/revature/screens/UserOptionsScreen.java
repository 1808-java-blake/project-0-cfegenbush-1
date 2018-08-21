package main.java.com.revature.screens;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import main.java.com.revature.beans.User;
import main.java.com.revature.daos.UserDao;
import main.java.com.revature.util.AppState;

public class UserOptionsScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private User currentUser = AppState.state.getCurrentUser();
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		/*
		 * Displays options for a user to edit their profile, or delete it all together
		 */
		log.debug("started user options screen");
		System.out.println(" ");
		System.out.println("***************************************************");
		System.out.println("*                   USER PROFILE                  *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.println(" Enter 1 to view profile");
		System.out.println(" Enter 2 to change username");
		System.out.println(" Enter 3 to change password");
		System.out.println(" Enter 4 to change first name");
		System.out.println(" Enter 5 to change last name");
		System.out.println(" Enter 6 to delete user profile");
		System.out.println(" Enter 7 to return to the previous screen");
		String selection = scan.nextLine();
		String currentUsername = currentUser.getUsername();
		switch (selection) {
		case "1":
			System.out.println(" ");
			System.out.println(" Username: " + currentUser.getUsername());
			System.out.println(" First Name: " + currentUser.getFirstName());
			System.out.println(" Last Name: " + currentUser.getLastName());
			System.out.println(" ");
			try {
				System.out.println(" Press Enter to continue ");
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "2":
			System.out.print(" Enter a new username:   ");
			String newUsername = scan.nextLine();
			currentUser.setUsername(newUsername);
			ud.updateUser(currentUser, currentUsername);
			break;
		case "3":
			System.out.print(" Enter old password:   ");
			String oldPassword = scan.nextLine();
			if (currentUser.getPassword().equals(oldPassword)) {
				System.out.print(" Enter a new password:   ");
				String newPassword = scan.nextLine();
				currentUser.setPassword(newPassword);
				ud.updateUser(currentUser, currentUsername);
				break;
			}
			System.out.println(" Password incorrect. Please try again. ");
			return this;
		case "4":
			System.out.print(" Enter a new first name: ");
			String newFirst = scan.nextLine();
			currentUser.setFirstName(newFirst);
			ud.updateUser(currentUser, currentUsername);
			break;
		case "5":
			System.out.print(" Enter a new last name:  ");
			String newLast = scan.nextLine();
			currentUser.setLastName(newLast);
			ud.updateUser(currentUser, currentUsername);
			break;
		case "6":
			System.out.println(" Are you sure you want to delete this profile and all associated accounts?");
			String deleteOrNot = scan.nextLine();
			if ("yes".equalsIgnoreCase(deleteOrNot)) {
				log.trace("deleting user: " + currentUser.getUsername());
				ud.deleteUser(currentUser);
				log.debug("User deleted successfully");
				return new LoginScreen();
			} else if ("no".equalsIgnoreCase(deleteOrNot)) {
				break;
			}
		case "7":
			return new HomeScreen();
		}
		return this;
	}

}
