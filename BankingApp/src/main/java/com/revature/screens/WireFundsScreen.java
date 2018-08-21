package main.java.com.revature.screens;

import java.util.Scanner;
import org.apache.log4j.Logger;
import main.java.com.revature.beans.Account;
import main.java.com.revature.daos.AccountDao;
import main.java.com.revature.util.AppState;

public class WireFundsScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private AccountDao ad = AccountDao.currentAccountDao;
	private Account currentAccount = AppState.state.getCurrentAccount();
	private Account targetAccount;
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		System.out.println(" ");
		System.out.println("***************************************************");
		System.out.println("*                    WIRE FUNDS                   *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.print(" Enter the target account number:  ");
		int accountNumber = scan.nextInt();

		try {
			targetAccount = ad.getAccount(accountNumber);
			System.out.println(" Enter the amount to transfer: ");
			System.out.print("$  ");
			double amountToTransfer = scan.nextInt();
			if (currentAccount.getBalance() - amountToTransfer < 0) {
				System.out.println(" ");
				System.out.println("  Insufficient Funds.");
				return new AccountHomeScreen();
			}
			ad.makeWithdrawal(currentAccount, amountToTransfer);
			ad.makeDeposit(targetAccount, amountToTransfer);
			ad.addWireTransferTransaction(currentAccount, targetAccount, amountToTransfer, "Wire Transfer");
			return new AccountHomeScreen();
		} catch (Exception e) {
			System.out.println(" Account number is incorrect or does not exist");
			log.error("Failed to find account with given account number");
			return this;
		}

	}

}
