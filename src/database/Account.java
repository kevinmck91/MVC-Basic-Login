package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Account {

	private Connection conn;
	private static String message = "Incorrect Details";

	public Account(Connection conn){
		this.conn = conn;
	}

	public boolean login(String email, String password) {


		String sql = "select count(*) as count from logindetails where email = ? and password=?";

		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);


			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			int count = 0;

			if(rs.next()){
				count = rs.getInt("count");
			}

			rs.close();

			if(count ==0){
				return false;
			}else{
				return true;
			}

		} catch (SQLException e) {
			return false;
		}
	}

	public static String getMessage() {
		return message;
	}
}
