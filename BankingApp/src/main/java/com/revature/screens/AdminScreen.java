package main.java.com.revature.screens;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import main.java.com.revature.beans.Account;
import main.java.com.revature.daos.AccountDao;
import main.java.com.revature.daos.UserDao;

public class AdminScreen implements Screen {
	Scanner scan = new Scanner(System.in);
	AccountDao ad = AccountDao.currentAccountDao;
	UserDao ud = UserDao.currentUserDao;
	Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		log.debug("started admin screen");
		/*
		 * Admin views list of all users to select from Then views list of all accounts
		 * belonging to that user Selecting an account displays the account's
		 * transaction history
		 */
		Account a = new Account();
		System.out.println("***************************************************");
		System.out.println("*                      ADMIN                      *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.println(" Which user would you like to access?");
		List<String> usernames = ud.getAllUsernames();

		log.info(usernames);
		for (int i = 0; i < usernames.size(); i++) {
			System.out.println(usernames.get(i));
		}
		String userSelection = scan.nextLine();
		log.trace("user selected=" + userSelection);

		if (ud.getUserAccounts(userSelection).isEmpty()) {
			System.out.println(" Failed to find accounts for that user. Please try again.");
			return new AdminScreen();
		}

		System.out.println(" Which account's history would you like to view?");

		List<Integer> userAccounts = ud.getUserAccounts(userSelection);
		log.info(userAccounts);
		for (int i = 0; i < userAccounts.size(); i++) {
			System.out.println(userAccounts.get(i));
		}

		int accountSelection = scan.nextInt();
		log.trace("selected account= " + accountSelection);

		a = ad.getAccount(accountSelection);

		if (a == null) {
			System.out.println("Failed to find account for the number provided. Please try again.");
			return new AdminScreen();
		}

		for (int i = 0; i < ad.getTransactionHistory(a.getAccountNumber()).size(); i++) {
			System.out.println(ad.getTransactionHistory(a.getAccountNumber()).get(i).toString());
		}

		log.debug("retrieved account transaction history");

		System.out.println(" ");
		System.out.println("Enter 1 to restart");
		System.out.println("Enter 2 to log out");
		int exit = scan.nextInt();

		if (2 == exit) {
			return new LoginScreen();
		} else {
			return new AdminScreen();
		}
	}
}
