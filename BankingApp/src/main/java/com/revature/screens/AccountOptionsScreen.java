package main.java.com.revature.screens;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.User;
import main.java.com.revature.daos.AccountDao;
import main.java.com.revature.daos.UserDao;
import main.java.com.revature.util.AppState;

public class AccountOptionsScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private AccountDao ad = AccountDao.currentAccountDao;
	private UserDao ud = UserDao.currentUserDao;
	private AppState state = AppState.state;
	private User currentUser = AppState.state.getCurrentUser();
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		/*
		 * User can select an account from a list of their accounts - assuming they have
		 * at least one User can create or delete an account
		 */
		log.debug("started account options screen");
		System.out.println("***************************************************");
		System.out.println("*                 ACCOUNT OPTIONS                 *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.println("   Please choose an option:");
		System.out.println(" ");
		System.out.println("   1: Select Account");
		System.out.println("   2: Create Account");
		System.out.println("   3: Return to the previous screen");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			List<Integer> userAccounts = ud.getUserAccounts(currentUser.getUsername());
			if (userAccounts.size() != 0) {
				System.out.println("   Account(s): ");
				for (int userAccount : userAccounts) {
					Account a = ad.getAccount(userAccount);
					System.out.printf("   %s - %s", userAccount, a.getAccountType());
					System.out.println(" ");
				}

				log.trace("retrieved user accounts");
				int selectedAccount;
				try {
					selectedAccount = scan.nextInt();
					log.trace("selected account= " + selectedAccount);
				} catch (InputMismatchException e) {
					System.out.println(" Please enter a valid account number. ");
					return new AccountOptionsScreen();
				} finally {
					scan.nextLine();
				}

				if (userAccounts.contains(selectedAccount)) {
					state.setCurrentAccount(ad.getAccount(selectedAccount));
					log.debug("selected account: " + state.getCurrentAccount());
					return new AccountHomeScreen();
				}
				System.out.println("   Incorrect Account Number. Please try again");
				return this;
			}
			System.out.println("   No accounts available. Create an account.");
			return this;
		case "2":
			return new CreateAccountScreen();
		case "3":
			return new HomeScreen();
		}
		return this;
	}

}
