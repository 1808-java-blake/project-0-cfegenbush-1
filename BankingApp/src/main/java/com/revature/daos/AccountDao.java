package main.java.com.revature.daos;

import java.util.List;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.Transaction;

public interface AccountDao {

	public static final AccountDao currentAccountDao = new AccountDaoJdbc();

	/*
	 * Takes in Account object and will persist that account
	 * 
	 * @param a
	 */

	void createAccount(Account a, String username);

	Account getAccount(int accountNumber);

	void makeDeposit(Account a, double amountToDeposit);

	void makeWithdrawal(Account a, double amountToWithdraw); // throws insufficient funds exception

	void deleteAccount(int accountNumber);

	void addTransaction(Account currentAccount, String transactionType, double amount);

	void addWireTransferTransaction(Account outgoingAccount, Account incomingAccount, double amount, String transactionType);

	void addAccountOwner(int currentAccountNumber, String username);

	List<Transaction> getTransactionHistory(int accountNumber);

}
