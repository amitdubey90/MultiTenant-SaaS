package app.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.data.UserInfo;
import app.utilities.ApplicationUtility;
import app.utilities.DatabaseConnection;
import app.utilities.DatabaseQueries;

public class SignUpUserService {

	public int addUser(UserInfo user) {
		int userId = 0;
		try {
			Connection dbCon = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = dbCon
					.prepareStatement(DatabaseQueries.SIGNUP_USER);
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getLastname());
			preparedStatement.setString(3, user.getAddress());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setLong(5, user.getPhone());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.executeUpdate();

			preparedStatement = dbCon
					.prepareStatement(DatabaseQueries.GET_USER_ID);
			preparedStatement.setString(1, user.getEmail());
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			userId = rs.getInt("userId");

			DatabaseConnection.closeConnection(dbCon);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return userId;
	}

	public boolean login(UserInfo signupUser) {
		try {
			System.out.println(signupUser.getEmail()+" "+signupUser.getPassword());
			Connection dbCon = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = dbCon
					.prepareStatement(DatabaseQueries.GET_USER_INFO_BY_MAIL);
			preparedStatement.setString(1, signupUser.getEmail());
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			String email = rs.getString("Email");
			String password = rs.getString("password");
			System.out.println(email+" "+password);
			if (!ApplicationUtility.checkNull(signupUser.getEmail())
					&& !ApplicationUtility.checkNull(signupUser.getPassword())) {
				if (signupUser.getEmail().equalsIgnoreCase(email)
						&& signupUser.getPassword().equalsIgnoreCase(password)) {
					return true;
				}
			}

			DatabaseConnection.closeConnection(dbCon);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
