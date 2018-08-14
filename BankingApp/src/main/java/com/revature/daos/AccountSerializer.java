package main.java.com.revature.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.User;

public class AccountSerializer implements AccountDao {

	@Override
	public void createAccount(Account a) {
		if (a == null) {
			return;
		}
		File f = new File("src/main/resources/accounts/" + a.getAccountNumber() + ".txt");
		if (f.exists()) {
			return;
		}
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/main/resources/accounts/" + a.getAccountNumber() + ".txt"))) {
			oos.writeObject(a);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public Account getAccount(int accountNumber) {
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("src/main/resources/accounts/" + accountNumber + ".txt"))) {
			
			Account a = (Account) ois.readObject();
			return a;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void makeDeposit(Account a, double amountToDeposit) {
		double newBalance = a.getBalance() + amountToDeposit;
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String transaction = timeStamp + " - Deposit of $" + amountToDeposit;
		a.setBalance(newBalance);
		a.setTransactionHistory(transaction);
	}

	@Override
	public void makeWithdrawal(Account a, double amountToWithdraw) {
		double newBalance = a.getBalance() - amountToWithdraw;
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String transaction = timeStamp + " - Withdrawal of $" + amountToWithdraw;
		a.setBalance(newBalance);
		a.setTransactionHistory(transaction);
	}

	@Override
	public void updateAccount(Account a, User u) {
		if (a == null) {
			return;
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/main/resources/accounts/" + u.getUsername() + a.getAccountType() + ".txt"))) {
			oos.writeObject(a);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAccount(int accountNumber) {
		boolean deleted = false;
		try {
			File f = new File("src/main/resources/accounts/" + accountNumber + ".txt");
			deleted = f.delete();
			System.out.println("File deleted: " + deleted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
