package main.java.com.revature.screens;

import java.io.File;
import java.util.Scanner;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.User;
import main.java.com.revature.daos.AccountDao;

public class WireFundsScreen implements Screen {
	Scanner scan = new Scanner(System.in);
	AccountDao ad = AccountDao.currentAccountDao;
	Account a;
	User currentUser;
	
	public WireFundsScreen(Account a, User currentUser) {
		super();
		this.a = a;
		this.currentUser = currentUser;
	}

	@Override
	public Screen start() {
		System.out.println("***************************************************");
		System.out.println("*                    WIRE FUNDS                   *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.println(" Enter the account number  ");
		String accountNumber = scan.nextLine();
		
		File accountsFolder = new File("src/main/resources/accounts");
		String[] listOfAccounts = accountsFolder.list();
		if (listOfAccounts.toString().contains(accountNumber)) {
			System.out.println(" Enter the amount to transfer: $ ");
			int amount = scan.nextInt();
			ad.makeWithdrawal(a, amount);
			ad.updateAccount(a);
			Account targetAccount = a;
			targetAccount = ad.getAccount(Integer.parseInt(accountNumber));
			ad.makeDeposit(targetAccount, amount);
			ad.updateAccount(targetAccount);
		}
		
		return new AccountHomeScreen(a, currentUser);
	}

}
