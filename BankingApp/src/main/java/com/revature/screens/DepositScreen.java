package main.java.com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import main.java.com.revature.beans.Account;
import main.java.com.revature.daos.AccountDao;
import main.java.com.revature.util.AppState;

public class DepositScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private AccountDao ad = AccountDao.currentAccountDao;
	private Account currentAccount = AppState.state.getCurrentAccount();
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		log.debug("started deposit screen");
		System.out.println("***************************************************");
		System.out.println("*                     DEPOSITS                    *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.println("   Enter Deposit Amount: $");
		double amountToDeposit = scan.nextDouble();
		ad.makeDeposit(currentAccount, amountToDeposit);
		ad.addTransaction(currentAccount, currentAccount, "Deposit", amountToDeposit);
		return new AccountHomeScreen();
	}

}
