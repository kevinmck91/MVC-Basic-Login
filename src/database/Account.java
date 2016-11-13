package database;

import java.sql.Connection;



public class Account {
	
	private Connection conn;
	private static String message = "Incorrect Details";
	
	public Account(Connection conn){
		this.conn = conn;
	}

	public boolean login(String email, String password){
		return false;
	}
	
	public static String getMessage() {
		return message;
	}
}
