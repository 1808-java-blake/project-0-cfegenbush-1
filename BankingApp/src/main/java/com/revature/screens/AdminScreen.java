package main.java.com.revature.screens;

import java.io.File;
import java.util.Scanner;

import main.java.com.revature.beans.Account;
import main.java.com.revature.daos.AccountDao;

public class AdminScreen implements Screen {
	Scanner scan = new Scanner(System.in);
	AccountDao ad = AccountDao.currentAccountDao;

	@Override
	public Screen start() {
		/*
		 * Admin views list of all users to select from
		 * Then views list of all accounts belonging to that user
		 * Selecting an account displays the account's transaction history
		 */
		Account a = new Account();
		System.out.println("Which user would you like to access?");
		File usersFolder = new File("src/main/resources/users");
		File[] listOfUsers = usersFolder.listFiles();
		
		for (int i = 0; i < listOfUsers.length; i++) {
			if (listOfUsers[i].isFile() && !"admin.txt".equals(listOfUsers[i].getName())) {
				System.out.println(listOfUsers[i].getName().replaceAll(".txt", ""));
			}
		}
		String userSelection = scan.nextLine();
		
		System.out.println("Which account's history would you like to view?");
		
		
		File accountsFolder = new File("src/main/resources/accounts");
		File[] listOfAccounts = accountsFolder.listFiles();
		for (int i = 0; i < listOfAccounts.length; i++) {
			if (listOfAccounts[i].isFile()) {
				a = ad.getAccount(Integer.parseInt(listOfAccounts[i].getName().replaceAll(".txt", "")));
				if (a.getAccountOwners().contains(userSelection)) {
					System.out.println(a.getAccountNumber());
				} else {
					System.out.println("There are no accounts for that user");
					return new AdminScreen();
				}
			}
		}
		
		int accountSelection = scan.nextInt();
		
		try {
			a = ad.getAccount(accountSelection);
			for (int i = 0; i < a.getTransactionHistory().size(); i++) {
				System.out.println(a.getTransactionHistory().get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Enter 1 to restart");
		System.out.println("Enter 2 to log out");
		String exit = scan.nextLine();
		if ("1".equals(exit)) { 
			return new AdminScreen();
		} else if ("2".equals(exit)) { 
			return new LoginScreen(); 
		}
		return null;
	}
}
