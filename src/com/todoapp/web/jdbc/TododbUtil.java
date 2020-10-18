package com.todoapp.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class TododbUtil {
	private DataSource dataSource;

	public TododbUtil(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}

	public boolean checkUser(String emailOrUsername, String password) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		System.out.println("aaa");
		boolean rs = false;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from user where password = ? and email = ? or username = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, password);
			myStmt.setString(2, emailOrUsername);
			myStmt.setString(3, emailOrUsername);
			rs = myStmt.execute();
			System.out.println(rs);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
		return rs;
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myStmt != null)
				myStmt.close();
			if (myRs != null)
				myRs.close();
			if (myConn != null)
				myConn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
