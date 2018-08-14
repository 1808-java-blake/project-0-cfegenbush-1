package main.java.com.revature.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.java.com.revature.beans.User;

public class UserSerializer implements UserDao {

	@Override
	public void createUser(User u) {
		if (u == null) {
			return;
		}
		File f = new File("src/main/resources/users/" + u.getUsername() + ".txt");
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
				new FileOutputStream("src/main/resources/users/" + u.getUsername() + ".txt"))) {
			oos.writeObject(u);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		if (username == null || password == null) {
			return null;
		}
		
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("src/main/resources/users/" + username + ".txt"))) {
			
			User u = (User) ois.readObject();
			if(password.equals(u.getPassword())) {
				return u;
			} else {
				return null;
			}
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
	public void updateUser(User u) {
		if (u == null) {
			return;
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/main/resources/users/" + u.getUsername() + ".txt"))) {
			oos.writeObject(u);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteUser(User u) {
		boolean deleted = false;
		try {
			File f = new File("src/main/resources/users/" + u.getUsername() + ".txt");
			deleted = f.delete();
			System.out.println("File deleted: " + deleted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
