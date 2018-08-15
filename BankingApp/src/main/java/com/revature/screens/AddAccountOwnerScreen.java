package main.java.com.revature.screens;

import java.io.File;
import java.util.Scanner;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.User;
import main.java.com.revature.daos.AccountDao;
import main.java.com.revature.daos.UserDao;

public class AddAccountOwnerScreen implements Screen {

	Scanner scan = new Scanner(System.in);
	Account a;
	AccountDao ad = AccountDao.currentAccountDao;
	UserDao ud = UserDao.currentUserDao;
	User currentUser;
	

	public AddAccountOwnerScreen(Account a, User currentUser) {
		super();
		this.a = a;
		this.currentUser = currentUser;
		// TODO Auto-generated constructor stub
	}



	@Override
	public Screen start() {
		System.out.println("***************************************************");
		System.out.println("*                  SHARE ACCOUNT                  *");
		System.out.println("***************************************************");
		System.out.println(" ");
		System.out.println(" Enter the username of the new account owner:");
		String username = scan.nextLine();
		System.out.println(" Enter the password of the new account owner:");
		String password = scan.next();
		
		User newAccountOwner = ud.findByUsernameAndPassword(username, password);	
		
		File usersFolder = new File("src/main/resources/users");
		String[] listOfUsers = usersFolder.list();
		if (listOfUsers.toString().contains(username)) {
			a.setAccountOwners(username);
			ad.updateAccount(a);
			newAccountOwner.setUserAccounts(a.getAccountNumber());
			ud.updateUser(newAccountOwner);
		}
		return new AccountHomeScreen(a, currentUser);
	}

}
