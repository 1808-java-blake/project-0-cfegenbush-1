package main.java.com.revature.screens;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.log4j.Logger;

import main.java.com.revature.beans.Account;
import main.java.com.revature.daos.AccountDao;

public class AdminScreen implements Screen {
	Scanner scan = new Scanner(System.in);
	AccountDao ad = AccountDao.currentAccountDao;
	Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		log.debug("started admin screen");
		/*
		 * Admin views list of all users to select from
		 * Then views list of all accounts belonging to that user
		 * Selecting an account displays the account's transaction history
		 */
		Account a = new Account();
		System.out.println("***************************************************");
		System.out.println("*                      ADMIN                      *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.println(" Which user would you like to access?");
		File usersFolder = new File("src/main/resources/users");
		String[] listOfUsers = usersFolder.list();
		
		log.info(listOfUsers);
		for (int i = 0; i < listOfUsers.length; i++) {
			if (!"admin.txt".equals(listOfUsers[i])) {
				System.out.println(listOfUsers[i].replaceAll(".txt", ""));
			}
		}
		String userSelection = scan.nextLine();
		log.trace("user selected=" + userSelection);
		
		System.out.println(" Which account's history would you like to view?");
		
		
		File accountsFolder = new File("src/main/resources/accounts");
		String[] listOfAccounts = accountsFolder.list();
		log.info(listOfAccounts);
		for (int i = 0; i < listOfAccounts.length; i++) {
			a = ad.getAccount(Integer.parseInt(listOfAccounts[i].replaceAll(".txt", "")));
			log.debug("retrieved account");
			if (a != null) {
			//if (a.getAccountOwners().contains(userSelection)) {
				System.out.println(a.getAccountNumber());
			} else {
				log.debug("Selected user does not have an account");
				System.out.println(" There are no accounts for that user");
				return new AdminScreen();
			}
		}
		
		int accountSelection = scan.nextInt();
		log.trace("selected account= " + accountSelection);
		
		if (Arrays.toString(listOfAccounts).replaceAll(".txt", "").contains(Integer.toString(accountSelection))) {
			a = ad.getAccount(accountSelection);
			log.debug("retrieved selected account");
		} else {
			log.debug("did not retrieve selected account");
			System.out.println(" Incorrect account number.");
			return new AdminScreen();
		}
		for (int i = 0; i < a.getTransactionHistory().size(); i++) {
			System.out.println(a.getTransactionHistory().get(i));
		}
		
		log.debug("retrieved account transaction history");
		
		System.out.println("Enter 1 to restart");
		System.out.println("Enter 2 to log out");
		String exit = scan.nextLine();
		
		if ("2".equals(exit)) { 
			return new LoginScreen();
		} else {
		return this;
		}
	}
}
