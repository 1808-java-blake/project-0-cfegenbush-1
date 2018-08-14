package main.java.com.revature.screens;

import java.util.Scanner;

import main.java.com.revature.beans.User;

public class HomeScreen implements Screen {
	Scanner scan = new Scanner(System.in);
	private User currentUser;

	public HomeScreen(User currentUser) {
		super();
		this.currentUser = currentUser;
	}



	@Override
	public Screen start() {
		System.out.println("Enter 1 for account options");
		System.out.println("Enter 2 for user options");
		System.out.println("Enter 3 to exit");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			return new AccountOptionsScreen(currentUser);
		case "2":
			return new UserOptionsScreen(currentUser);
		case "3":
			return new LoginScreen();
		}
		return this;
	}
}
