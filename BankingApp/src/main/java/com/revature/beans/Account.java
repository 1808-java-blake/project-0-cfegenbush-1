package main.java.com.revature.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
	/**
	 * 
	 */
	private List<String> accountOwners;
	private List<String> transactionHistory;
	private String accountType;
	private int accountNumber;
	private double balance;
	public static int nextAccountNumber = 1000;

	public Account() {
		super();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String creationString = timeStamp + " - Account Created";
		this.transactionHistory = new ArrayList<String>();
		this.transactionHistory.add(creationString);
		this.accountNumber = ++nextAccountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<String> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(String transaction) {
		this.transactionHistory.add(transaction);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setNewAccountNumber() {
		accountNumber++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + ((accountOwners == null) ? 0 : accountOwners.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transactionHistory == null) ? 0 : transactionHistory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (accountOwners == null) {
			if (other.accountOwners != null)
				return false;
		} else if (!accountOwners.equals(other.accountOwners))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (transactionHistory == null) {
			if (other.transactionHistory != null)
				return false;
		} else if (!transactionHistory.equals(other.transactionHistory))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountOwners=" + accountOwners + ", transactionHistory=" + transactionHistory
				+ ", accountType=" + accountType + ", accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

}
