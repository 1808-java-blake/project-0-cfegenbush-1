package main.java.com.revature.launcher;

import main.java.com.revature.screens.LoginScreen;
import main.java.com.revature.screens.Screen;

public class Launcher {
	
	public static void main(String[] args) {
		Screen s = new LoginScreen();
		while(true) {
			s = s.start();
		}
	}

}
