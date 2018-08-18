package main.java.com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import main.java.com.revature.daos.AccountDao;
import main.java.com.revature.daos.UserDao;
import main.java.com.revature.util.AppState;

public class AddAccountOwnerScreen implements Screen {

	private Scanner scan = new Scanner(System.in);
	private AccountDao ad = AccountDao.currentAccountDao;
	private UserDao ud = UserDao.currentUserDao;
	private int currentAccountNumber = AppState.state.getCurrentAccount().getAccountNumber();
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		log.debug("started add account owner screen");
		System.out.println("***************************************************");
		System.out.println("*                  SHARE ACCOUNT                  *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.print(" Enter the username of the new account owner:   ");
		String username = scan.nextLine();

		if (ud.verifyUserExistsForAccountSharing(username) == null) {
			System.out.println(" User does not exist");
			return this;
		}
		ad.addAccountOwner(currentAccountNumber, username);

		return new AccountHomeScreen();
	}

}
