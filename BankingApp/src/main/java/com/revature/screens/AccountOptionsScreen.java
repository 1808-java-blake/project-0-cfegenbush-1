package main.java.com.revature.screens;

import java.util.Scanner;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.User;
import main.java.com.revature.daos.AccountDao;

public class AccountOptionsScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private User currentUser;
	private AccountDao ad = AccountDao.currentAccountDao;

	public AccountOptionsScreen(User currentUser) {
		super();
		this.currentUser = currentUser;
	}

	@Override
	public Screen start() {
		/*
		 * User can select an account from a list of their accounts - assuming they have at least one
		 * User can create or delete an account
		 */
                System.out.println("***************************************************");
                System.out.println("*                 ACCOUNT OPTIONS                 *");
                System.out.println("***************************************************");
                System.out.println(" ");
		System.out.println("   Please choose an option:");
                System.out.println(" ");
		System.out.println("   1: Select Account");
		System.out.println("   2: Create Account");
		System.out.println("   3: Delete Account");
		System.out.println("   4: Return to the previous screen");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			if (currentUser.getUserAccounts().size() != 0) {
				Account a = new Account();
				System.out.println("Account(s): ");
				for (int userAccount: currentUser.getUserAccounts()) {
					a = ad.getAccount(userAccount);
					System.out.printf("%s - %s", userAccount, a.getAccountType());
					System.out.println(" ");
				}
				
				int selectedAccount = scan.nextInt();
				if (currentUser.getUserAccounts().contains(selectedAccount)) {
					a = ad.getAccount(selectedAccount);
					return new AccountHomeScreen(a, currentUser);
				}
			} 
			System.out.println("No accounts available. Create an account.");
			return this;
		case "2":
			return new CreateAccountScreen(currentUser);
		case "3":
			if (currentUser.getUserAccounts().size() != 0) {
				Account a = new Account();
				System.out.println("Select account to delete: ");
				for (int userAccount: currentUser.getUserAccounts()) {
					a = ad.getAccount(userAccount);
					System.out.printf("%s - %s", userAccount, a.getAccountType());
				}
				
				int selectedAccount = scan.nextInt();
				if (currentUser.getUserAccounts().contains(selectedAccount)) {
					ad.deleteAccount(selectedAccount);
					return new LoginScreen();
				}
			}
			System.out.println("No accounts to delete");
			break;
		case "4":
			return new HomeScreen(currentUser);
		}
		return this;
	}

}
