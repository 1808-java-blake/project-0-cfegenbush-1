package main.java.com.revature.screens;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.Transaction;
import main.java.com.revature.daos.AccountDao;
import main.java.com.revature.util.AppState;

public class AccountHomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private AccountDao ad = AccountDao.currentAccountDao;
	private Account currentAccount = AppState.state.getCurrentAccount();
	private Logger log = Logger.getRootLogger();
	NumberFormat formatter = NumberFormat.getCurrencyInstance();

	@Override
	public Screen start() {
		/*
		 * Displays basic account operations as well as advanced options, namely - Wire
		 * funds, add account owner making the account sharable
		 */
		log.debug("started account home screen");
		System.out.println(" ");
		System.out.println("***************************************************");
		System.out.println("*                    ACCOUNT                      *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.println("  Please choose an option: ");
		System.out.println(" ");
		System.out.println("  1: Deposit ");
		System.out.println("  2: Withdrawal  ");
		System.out.println("  3: Balance Inquiry ");
		System.out.println("  4: Transaction History ");
		System.out.println("  5: Wire Funds ");
		System.out.println("  6: Add Account Owner ");
		System.out.println("  7: Delete Account ");
		System.out.println("  8: Previous Screen ");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			return new DepositScreen();
		case "2":
			return new WithdrawalScreen();
		case "3":
			double accountBalance = ad.getAccount(currentAccount.getAccountNumber()).getBalance();
			System.out.println(" ");
			System.out.println("  " + formatter.format(accountBalance));
			try {
				System.out.println(" ");
				System.out.println(" Press Enter to continue ");
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "4":
			System.out.println(" Transaction History:");
			List<Transaction> transactionHistory = ad.getTransactionHistory(currentAccount.getAccountNumber());
			if (transactionHistory.size() == 0) {
				System.out.println(" No transactions have been made.");
			} else {
				for (int i = 0; i < transactionHistory.size(); i++) {
					int target = transactionHistory.get(i).getIncomingAccount();
					if (target != 0) {
						System.out.println(transactionHistory.get(i).toString() + " | Target Account: " + target);
					} else {
						System.out.println(transactionHistory.get(i).toString());
					}
				}
			}
			try {
				System.out.println(" ");
				System.out.println(" Press Enter to continue ");
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "5":
			return new WireFundsScreen();
		case "6":
			return new AddAccountOwnerScreen();
		case "7":
			System.out.println(" Are you sure you want to delete this account? ");
			String deleteAnswer = scan.nextLine();
			if ("yes".equalsIgnoreCase(deleteAnswer)) {
				log.warn("deleting account " + currentAccount.getAccountNumber());
				ad.deleteAccount(currentAccount.getAccountNumber());
				log.debug("Account " + currentAccount.getAccountNumber() + " deleted");
			}
			return new AccountOptionsScreen();
		case "8":
			return new AccountOptionsScreen();
		}
		return this;
	}

}
