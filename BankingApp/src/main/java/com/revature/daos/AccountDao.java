package main.java.com.revature.daos;

import main.java.com.revature.beans.Account;

public interface AccountDao {

	public static final AccountDao currentAccountDao = new AccountDaoJdbc();

	/*
	 * Takes in Account object and will persist that account
	 * 
	 * @param a
	 */

	void createAccount(Account a);

	Account getAccount(int accountNumber);

	void makeDeposit(Account a, double amountToDeposit);

	void makeWithdrawal(Account a, double amountToWithdraw); // throws insufficient funds exception

	void updateAccount(Account a);

	void deleteAccount(int accountNumber);

	void addTransaction(Account currentAccount, Account targetAccount, String transactionType, double amount);

	void addAccountOwner(int currentAccountNumber, String username);

}
