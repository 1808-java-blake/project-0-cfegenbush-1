package main.java.com.revature.screens;

import java.util.Scanner;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.User;

public class AccountHomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private Account a;
	private User currentUser;

	public AccountHomeScreen(Account a, User currentUser) {
		super();
		this.a = a;
		this.currentUser = currentUser;
	}

	@Override
	public Screen start() {
		/*
		 * 
		 */
		System.out.println("Please choose from the following options: ");
		System.out.println("Enter 1 to make a deposit");
		System.out.println("Enter 2 to make a withdrawal");
		System.out.println("Enter 3 to view account balance");
		System.out.println("Enter 4 to view transaction history");
		System.out.println("Enter 5 to wire funds to an account");
		System.out.println("Enter 6 to share this account with another user");
		System.out.println("Enter 7 to return to the previous screen");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			System.out.println("Enter 1 to make a deposit");
			return new DepositScreen(a, currentUser);
		case "2":
			System.out.println("Enter 2 to make a withdrawal");
			return new WithdrawalScreen(a, currentUser);
		case "3":
			System.out.println("Enter 3 to view account balance");
			System.out.println("$" + a.getBalance());
			break;
		case "4":
			System.out.println("Transaction History:");
			for (int i = 0; i < a.getTransactionHistory().size(); i++) {
				System.out.println(a.getTransactionHistory().get(i));
			}
			break;
		case "7":
			return new AccountOptionsScreen(currentUser);
		}
		return this;
	}

}
