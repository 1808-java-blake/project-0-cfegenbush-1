package main.java.com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import main.java.com.revature.beans.Account;
import main.java.com.revature.daos.AccountDao;
import main.java.com.revature.util.AppState;

public class WithdrawalScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private AccountDao ad = AccountDao.currentAccountDao;
	private Account currentAccount = AppState.state.getCurrentAccount();
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		log.debug("started withdrawal screen");
		System.out.println("***************************************************");
		System.out.println("*                    WITHDRAWALS                  *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.println("   Enter Withdrawal Amount: $");
		double amountToWithdraw = scan.nextDouble();
		ad.makeWithdrawal(currentAccount, amountToWithdraw);
		ad.addTransaction(currentAccount, currentAccount, "Withdrawal", amountToWithdraw);
		return new AccountHomeScreen();
	}

}
