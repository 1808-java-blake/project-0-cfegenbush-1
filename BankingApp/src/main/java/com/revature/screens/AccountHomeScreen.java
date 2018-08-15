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
                System.out.println("***************************************************");
                System.out.println("*                    ACCOUNT                      *");
                System.out.println("***************************************************");
                System.out.println(" ");
		System.out.println("  Please choose an option: ");
                System.out.println(" ");
		System.out.println("  1: Deposit ");
		System.out.println("  2: Withdrawal  ");
		System.out.println("  3: Balance Inquiry ");
		System.out.println("  4: Transaction History ");
		System.out.println("  5: Wire Funds ");
		System.out.println("  6: Add Account Owner ");
		System.out.println("  7: Previous Screen ");
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
		case "5":
			return new WireFundsScreen(a, currentUser);
		case "6":
			return new AddAccountOwnerScreen(a, currentUser);
		case "7":
			return new AccountOptionsScreen(currentUser);
		}
		return this;
	}

}
