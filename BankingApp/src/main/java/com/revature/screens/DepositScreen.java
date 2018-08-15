package main.java.com.revature.screens;

import java.util.Scanner;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.User;
import main.java.com.revature.daos.AccountDao;

public class DepositScreen implements Screen {
	Scanner scan = new Scanner(System.in);
	private AccountDao ad = AccountDao.currentAccountDao;
	private Account a;
	private User currentUser;
	
	

	public DepositScreen(Account a, User currentUser) {
		super();
		this.a = a;
		this.currentUser = currentUser;
	}

	@Override
	public Screen start() {
                System.out.println("***************************************************");
                System.out.println("*                     DEPOSITS                    *");
                System.out.println("***************************************************");
                System.out.println(" ");
		System.out.println("   Enter Deposit Amount: $");
		double amountToDeposit = scan.nextDouble();
		ad.makeDeposit(a, amountToDeposit);
		ad.updateAccount(a);
		return new AccountHomeScreen(a, currentUser);
	}

}
