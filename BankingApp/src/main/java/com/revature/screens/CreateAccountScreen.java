package main.java.com.revature.screens;

import java.util.Scanner;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.User;
import main.java.com.revature.daos.AccountDao;
import main.java.com.revature.daos.UserDao;

public class CreateAccountScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private AccountDao ad = AccountDao.currentAccountDao;
	private UserDao ud = UserDao.currentUserDao;
	private User currentUser;
	
	

	public CreateAccountScreen(User currentUser) {
		super();
		this.currentUser = currentUser;
	}

	@Override
	public Screen start() {
		/*
		 * User can create an account from two types of accounts
		 */
		Account a = new Account();
		System.out.println("Account Options: Checking | Savings");
		System.out.println("Enter the type of account you wish to create, or 'Exit' to return:");
		String selection = scan.nextLine();
		// Need to add account numbers 
		if ("checking".equalsIgnoreCase(selection)) {
			String checking = "checking";
			a.setAccountType(checking);
			a.setAccountOwners(currentUser.getUsername());
			ad.createAccount(a);
			currentUser.setUserAccounts(a.getAccountNumber());
			ud.updateUser(currentUser);
			return new AccountHomeScreen(a, currentUser);
		} else if ("savings".equalsIgnoreCase(selection)) {
			String savings = "savings";
			a.setAccountType(savings);
			a.setAccountOwners(currentUser.getUsername());
			ad.createAccount(a);
			currentUser.setUserAccounts(a.getAccountNumber());
			ud.updateUser(currentUser);
			return new AccountHomeScreen(a, currentUser);
		} else if ("Exit".equalsIgnoreCase(selection)) {
			return new AccountOptionsScreen(currentUser);
		}
		return this;
	}

}
