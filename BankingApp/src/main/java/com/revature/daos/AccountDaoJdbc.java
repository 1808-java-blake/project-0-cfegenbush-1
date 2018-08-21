package main.java.com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import main.java.com.revature.beans.Account;
import main.java.com.revature.beans.Transaction;
import main.java.com.revature.util.ConnectionUtil;

public class AccountDaoJdbc implements AccountDao {

	private ConnectionUtil cu = ConnectionUtil.cu;
	private Logger log = Logger.getRootLogger();

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void createAccount(Account a, String username) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO Accounts (AccountNumber, AccountType, Balance, CreationDate, CreatedBy) VALUES"
							+ " (?, ?, ?, CURRENT_TIMESTAMP, ?)");

			ps.setInt(1, a.getAccountNumber());
			ps.setString(2, a.getAccountType());
			ps.setDouble(3, a.getBalance());
			ps.setString(4, username);

			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created in accounts");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new account");
		}

	}

	@Override
	public Account getAccount(int accountNumber) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Accounts WHERE AccountNumber=?");
			ps.setInt(1, accountNumber);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Account a = new Account();
				a.setAccountNumber(rs.getInt("AccountNumber"));
				a.setAccountType(rs.getString("AccountType"));
				a.setBalance(rs.getDouble("Balance"));
				return a;
			} else {
				log.warn("failed to find account with provided credentials from the db");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void makeDeposit(Account a, double amountToDeposit) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("UPDATE Accounts SET Balance =? WHERE AccountNumber = ?");
			double newBalance = a.getBalance() + amountToDeposit;
			a.setBalance(newBalance);
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getAccountNumber());

			int recordsUpdated = ps.executeUpdate();
			log.trace(recordsUpdated + " records updated in accounts");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to make deposit");
		}

	}

	@Override
	public void makeWithdrawal(Account a, double amountToWithdraw) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("UPDATE Accounts SET Balance =? WHERE AccountNumber = ?");
			double newBalance = a.getBalance() - amountToWithdraw;
			a.setBalance(newBalance);
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getAccountNumber());

			int recordsUpdated = ps.executeUpdate();
			log.trace(recordsUpdated + " records updated in accounts");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to make withdrawal");
		}

	}

	@Override
	public void deleteAccount(int accountNumber) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Accounts WHERE AccountNumber=?");
			ps.setInt(1, accountNumber);
			int result = ps.executeUpdate();
			log.trace(result + " account deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to delete user");
		}

	}

	@Override
	public void addTransaction(Account currentAccount, String transactionType, double amount) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO Transactions (TransactionType, Amount, Date, OutgoingAccount) "
							+ "VALUES (?, ?, CURRENT_TIMESTAMP, ?)");
			ps.setString(1, transactionType);
			ps.setDouble(2, amount);
			ps.setInt(3, currentAccount.getAccountNumber());

			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created in transactions");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new transaction");
		}

	}

	@Override
	public void addWireTransferTransaction(Account outgoingAccount, Account incomingAccount, double amount, String transactionType) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO Transactions (TransactionType, Amount, Date, OutgoingAccount, IncomingAccount) "
							+ "VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?)");
			ps.setString(1, transactionType);
			ps.setDouble(2, amount);
			ps.setInt(3, outgoingAccount.getAccountNumber());
			ps.setInt(4, incomingAccount.getAccountNumber());

			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created in transactions");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new transaction");
		}

	}

	@Override
	public void addAccountOwner(int currentAccountNumber, String username) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO AccountOwners (Username, AccountID) VALUES (?, ?)");
			ps.setString(1, username);
			ps.setInt(2, currentAccountNumber);

			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created in account owners");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new account owner");
		}

	}

	@Override
	public List<Transaction> getTransactionHistory(int accountNumber) {
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM Transactions WHERE OutgoingAccount = ? OR IncomingAccount = ? ORDER BY date");
			List<Transaction> transactionHistory = new ArrayList<>();
			ps.setInt(1, accountNumber);
			ps.setInt(2, accountNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionType(rs.getString("TransactionType"));
				transaction.setAmount(rs.getDouble("Amount"));
				transaction.setDate(rs.getDate("Date").toString());
				transaction.setIncomingAccount(rs.getInt("IncomingAccount"));
				transaction.setOutgoingAccount(rs.getInt("OutgoingAccount"));
				transactionHistory.add(transaction);
			}
			return transactionHistory;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
