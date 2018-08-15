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
                System.out.println("***************************************************");
                System.out.println("*                 CREATE ACCOUNT                  *");
                System.out.println("***************************************************");
                System.out.println(" ");
		System.out.println("    Please choose an account type:");
		System.out.println("    ");
		System.out.println("    1: Checking");
		System.out.println("    2: Savings");
		System.out.println("    3: Exit");
		String selection = scan.nextLine();switch (selection) {
		case "1":
			a.setAccountType("checking");
			a.setAccountOwners(currentUser.getUsername());
			ad.createAccount(a);
			currentUser.setUserAccounts(a.getAccountNumber());
			ud.updateUser(currentUser);
			return new AccountHomeScreen(a, currentUser);
		case "2":
			a.setAccountType("savings");
			a.setAccountOwners(currentUser.getUsername());
			ad.createAccount(a);
			currentUser.setUserAccounts(a.getAccountNumber());
			ud.updateUser(currentUser);
			return new AccountHomeScreen(a, currentUser);
		case "3":
			return new AccountOptionsScreen(currentUser);
		}
		return this;
	}

}
