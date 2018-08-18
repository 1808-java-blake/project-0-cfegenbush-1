package main.java.com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class HomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		/*
		 * User can access their account, user profile, or log out
		 */
		log.debug("started home screen");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("***************************************************");
		System.out.println("*                   MAIN MENU                     *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.println("    Please choose an option:");
		System.out.println(" ");
		System.out.println("    1: Accounts");
		System.out.println("    2: User");
		System.out.println("    3: Exit");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			return new AccountOptionsScreen();
		case "2":
			return new UserOptionsScreen();
		case "3":
			return new LoginScreen();
		}
		return this;
	}
}
