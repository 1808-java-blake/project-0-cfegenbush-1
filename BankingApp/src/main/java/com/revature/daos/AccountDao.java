package main.java.com.revature.daos;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.User;

public interface AccountDao {
	
	public static final AccountDao currentAccountDao = new AccountSerializer();
	
	void createAccount(Account a);
	Account getAccount(int accountNumber);
	void makeDeposit(Account a, double amountToDeposit);
	void makeWithdrawal(Account a, double amountToWithdraw); // throws insufficient funds exception
	void updateAccount(Account a);
	void deleteAccount(int accountNumber);

}
