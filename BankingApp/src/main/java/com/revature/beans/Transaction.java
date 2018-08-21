package main.java.com.revature.beans;

import java.text.NumberFormat;

public class Transaction {
	private String transactionType;
	private double amount;
	private String date;
	private int outgoingAccount;
	private int incomingAccount;
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	public Transaction() {
		super();
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getOutgoingAccount() {
		return outgoingAccount;
	}

	public void setOutgoingAccount(int outgoingAccount) {
		this.outgoingAccount = outgoingAccount;
	}

	public int getIncomingAccount() {
		return incomingAccount;
	}

	public void setIncomingAccount(int incomingAccount) {
		this.incomingAccount = incomingAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + incomingAccount;
		result = prime * result + outgoingAccount;
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (incomingAccount != other.incomingAccount)
			return false;
		if (outgoingAccount != other.outgoingAccount)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account: " + outgoingAccount + " | " + date + " | " + formatter.format(amount) + " | " + transactionType;
	}

	

	

}
