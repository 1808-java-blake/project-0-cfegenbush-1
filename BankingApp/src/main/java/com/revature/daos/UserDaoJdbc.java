package main.java.com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import main.java.com.revature.beans.User;
import main.java.com.revature.util.ConnectionUtil;

public class UserDaoJdbc implements UserDao {
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
	public void createUser(User u) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO Users (Username, Password, FirstName, LastName,Admin)" + " VALUES (?,?,?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());

			if ("admin".equalsIgnoreCase(u.getUsername())) {
				ps.setInt(5, 1);
			} else {
				ps.setInt(5, 0);
			}

			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new user");
		}

	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE Username=? AND Password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User u = new User();
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("LastName"));
				u.setUsername(rs.getString("Username"));
				u.setPassword(rs.getString("Password"));
				return u;
			} else {
				log.warn("failed to find user with provided credentials from the db");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUser(User u) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE Users SET Username=?, Password=?, FirstName=?, LastName=?" + " WHERE Username = ?");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getUsername());

			int result = ps.executeUpdate();
			log.trace(result + " user updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new user");
		}

	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE Username=?");
			ps.setString(1, u.getUsername());
			int result = ps.executeUpdate();
			log.trace(result + " user deleted");
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
	public String verifyUserExistsForAccountSharing(String username) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT Username FROM Users WHERE Username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User u = new User();
				u.setUsername(rs.getString("Username"));
				return u.getUsername();
			} else {
				log.warn("failed to find user with provided username from the db");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Integer> getUserAccounts(String username) {
		try (Connection conn = cu.getConnection()) {
			List<Integer> accountIds = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement("SELECT AccountID FROM AccountOwners WHERE Username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int i = rs.getInt("AccountID");
				accountIds.add(i);
			}
			return accountIds;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
