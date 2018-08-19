package main.java.com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import main.java.com.revature.beans.Account;
import main.java.com.revature.daos.AccountDao;
import main.java.com.revature.util.AppState;

public class CreateAccountScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private AccountDao ad = AccountDao.currentAccountDao;
	private String username = AppState.state.getCurrentUser().getUsername();
	private AppState state = AppState.state;
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		/*
		 * User can create an account from two types of accounts
		 */
		log.debug("started create account screen");
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
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			a.setNewAccountNumber();
			a.setAccountType("checking");
			a.setBalance(0.00);
			ad.createAccount(a, username);
			log.debug("Created account: " + a.toString());
			state.setCurrentAccount(a);
			ad.addAccountOwner(a.getAccountNumber(), username);
			return new AccountHomeScreen();
		case "2":
			a.setNewAccountNumber();
			a.setAccountType("savings");
			a.setBalance(0.00);
			ad.createAccount(a, username);
			log.debug("Created account: " + a.toString());
			state.setCurrentAccount(a);
			ad.addAccountOwner(a.getAccountNumber(), username);
			return new AccountHomeScreen();
		case "3":
			return new AccountOptionsScreen();
		}
		return this;
	}

}
