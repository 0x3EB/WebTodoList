package com.todoapp.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TododbUtil {
	private DataSource dataSource;

	public TododbUtil(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}

	public boolean checkUser(String emailOrUsername, String password) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		boolean rs = false;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from user where password = ? and email = ? or username = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, password);
			myStmt.setString(2, emailOrUsername);
			myStmt.setString(3, emailOrUsername);
			ResultSet result = myStmt.executeQuery();
			rs = result.next();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
		return rs;
	}

	@SuppressWarnings("resource")
	public User getUser(String emailOrUsername, String password) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		User u = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from user where password = ? and email = ? or username = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, password);
			myStmt.setString(2, emailOrUsername);
			myStmt.setString(3, emailOrUsername);
			ResultSet result = myStmt.executeQuery();
			if (result.next()) {
				String sqlRole = "select * from role where id = ?";
				myStmt = myConn.prepareStatement(sqlRole);
				myStmt.setString(1, result.getString("idrole"));
				ResultSet result2 = myStmt.executeQuery();
				if (result2.next()) {
					Role r = new Role(Integer.parseInt(result2.getString("id")), result2.getString("libelle"));
					if (r.getLibelle().equals("Student"))
						u = new User(Integer.parseInt(result.getString("id")), result.getString("username"),
								result.getString("password"), r);
					else {
						List<Class> listClasses = new ArrayList<Class>();
						String sqlClasses = "select * from instructorclasses where idInstructor = ?";
						myStmt = myConn.prepareStatement(sqlClasses);
						myStmt.setString(1, result.getString("id"));
						ResultSet result3 = myStmt.executeQuery();
						while (result3.next()) {
							String sqlGetClass = "select * from class where id = ?";
							myStmt = myConn.prepareStatement(sqlGetClass);
							myStmt.setString(1, result3.getString("idClass"));
							ResultSet result4 = myStmt.executeQuery();
							if (result4.next()) {
								Class c = new Class(Integer.parseInt(result3.getString("idClass")),
										result4.getString("name"));
								listClasses.add(c);
							}
						}
						u = new User(Integer.parseInt(result.getString("id")), result.getString("username"),
								result.getString("password"), r, listClasses);
					}
				}
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
		return u;
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
