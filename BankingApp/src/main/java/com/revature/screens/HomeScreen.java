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
			return new AccountOptionsScreen(currentUser);
		case "2":
			return new UserOptionsScreen(currentUser);
		case "3":
			return new LoginScreen();
		}
		return this;
	}
}
