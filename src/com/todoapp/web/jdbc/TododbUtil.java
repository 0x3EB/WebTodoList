package com.todoapp.web.jdbc;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.sql.DataSource;

import com.todoapp.web.entities.Classroom;
import com.todoapp.web.entities.Role;
import com.todoapp.web.entities.Todo;
import com.todoapp.web.entities.User;
import com.todoapp.web.security.Security;

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
	public User getUser(String emailOrUsername, String password, PublicKey key) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		User u = null;
		User instructor = null;
		Role instructorRole = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from role where libelle = 'instructor'";
			myStmt = myConn.prepareStatement(sql);
			ResultSet result = myStmt.executeQuery();
			if (result.next()) {
				instructorRole = new Role(Integer.parseInt(result.getString("id")), result.getString("libelle"));
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
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
					if (r.getLibelle().toUpperCase().equals(Role.STUDENT)) {
						List<Todo> todosDone = new ArrayList<Todo>();
						String sqlTodoDone = "select * from tododone where iduser = ?";
						myStmt = myConn.prepareStatement(sqlTodoDone);
						myStmt.setString(1, result.getString("id"));
						ResultSet resultTodosDone = myStmt.executeQuery();
						while (resultTodosDone.next()) {
							String getTodo = "select * from todo where id = ?";
							myStmt = myConn.prepareStatement(getTodo);
							myStmt.setString(1, resultTodosDone.getString("idtodo"));
							ResultSet todosresult = myStmt.executeQuery();
							if (todosresult.next()) {
								String getInstructorTodo = "select * from user where id = ?";
								myStmt = myConn.prepareStatement(getInstructorTodo);
								myStmt.setString(1, todosresult.getString("idinstructor"));
								ResultSet instructorTodo = myStmt.executeQuery();
								if (instructorTodo.next()) {
									List<Classroom> listClassesInstructor = new ArrayList<Classroom>();
									String sqlClassesInstrucor = "select * from instructorclasses where idInstructor = ?";
									myStmt = myConn.prepareStatement(sqlClassesInstrucor);
									myStmt.setString(1, result.getString("id"));
									ResultSet resultInstructorClass = myStmt.executeQuery();
									while (resultInstructorClass.next()) {
										String sqlGetClass = "select * from class where id = ?";
										myStmt = myConn.prepareStatement(sqlGetClass);
										myStmt.setString(1, resultInstructorClass.getString("idClass"));
										ResultSet resultInstructorClassList = myStmt.executeQuery();
										if (resultInstructorClassList.next()) {
											Classroom c = new Classroom(
													Integer.parseInt(resultInstructorClassList.getString("idClass")),
													resultInstructorClassList.getString("name"));
											listClassesInstructor.add(c);
										}
									}
									instructor = new User(Integer.parseInt(instructorTodo.getString("id")),
											instructorTodo.getString("name"), instructorTodo.getString("prenom"),
											instructorTodo.getString("username"), instructorTodo.getString("password"),
											instructorRole, listClassesInstructor);
								}
								String id = Security.EncryptedString(todosresult.getString("id"), key);
								Todo t = new Todo(id, todosresult.getString("description"), instructor);
								todosDone.add(t);
							}
						}
						u = new User(Integer.parseInt(result.getString("id")), result.getString("name"),
								result.getString("lastname"), result.getString("username"),
								result.getString("password"), todosDone, r);
					} else if (r.getLibelle().toUpperCase().equals(Role.INSTRUCTOR)) {
						List<Classroom> listClasses = new ArrayList<Classroom>();
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
								Classroom c = new Classroom(Integer.parseInt(result3.getString("idClass")),
										result4.getString("name"));
								listClasses.add(c);
							}
						}
						u = new User(Integer.parseInt(result.getString("id")), result.getString("name"),
								result.getString("lastname"), result.getString("username"),
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

	public Todo getTodo(String id, PrivateKey key) throws InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from todo where id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, Security.decrypt(id, key));
			ResultSet res = myStmt.executeQuery();
			if (res.next()) {
				Todo t = new Todo(id, res.getString("description"));
				return t;
			}

		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}

		return null;
	}

	public List<Todo> getStudentTodo(User u, PublicKey key) {
		List<Todo> listTodos = new ArrayList<Todo>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		User instructor = null;
		String sql;
		try {
			myConn = dataSource.getConnection();
			sql = "select * from todoclass where idClass=(SELECT idclass FROM user WHERE id=?)";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, Integer.toString(u.getId()));
			ResultSet result = myStmt.executeQuery();
			while (result.next()) {
				sql = "SELECT * FROM todo where id=?";
				PreparedStatement myStmt1 = myConn.prepareStatement(sql);
				myStmt1.setString(1, result.getString("idtodo"));
				ResultSet res = myStmt1.executeQuery();
				while (res.next()) {
					sql = "select * from user where id=?";
					myStmt = myConn.prepareStatement(sql);
					myStmt.setString(1, res.getString("idinstructor"));
					ResultSet result2 = myStmt.executeQuery();
					if (result2.next()) {
						instructor = new User(result2.getString("name"), result2.getString("lastname"),
								result2.getString("username"), result2.getString("email"));
					}
					
					
					String id = Security.EncryptedString(res.getString("id"), key);
					Todo t = new Todo(id, res.getString("description"), instructor);
					listTodos.add(t);
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(null, myStmt, null);
		}
		return listTodos;
	}

	public List<Todo> getInstructorTodo(User u, PublicKey key) {
		List<Todo> listTodos = new ArrayList<Todo>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from todo where idInstructor=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, Integer.toString(u.getId()));
			ResultSet result = myStmt.executeQuery();
			while (result.next()) {
				String id = Security.EncryptedString(result.getString("id"), key);
				Todo t = new Todo(id, result.getString("description"), u);
				listTodos.add(t);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
		return listTodos;
	}

	public List<Classroom> getAllClassroom() {
		List<Classroom> lst = new ArrayList<Classroom>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from class";
			myStmt = myConn.prepareStatement(sql);
			ResultSet result = myStmt.executeQuery();
			while (result.next()) {
				boolean archivage = (Integer.parseInt(result.getString("archivage"))==1);
				Classroom c = new Classroom(Integer.parseInt(result.getString("id")), result.getString("name"), archivage);
				sql = "SELECT * FROM user WHERE idrole=1 AND idClass=?";
				PreparedStatement myStmt1 = myConn.prepareStatement(sql);
				myStmt1.setString(1, result.getString("id"));
				ResultSet res = myStmt1.executeQuery();
				while (res.next()) {
					User u = new User(res.getString("name"), res.getString("lastname"), res.getString("username"),
							res.getString("email"));
					c.addEleve(u);
				}
				lst.add(c);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
		return lst;
	}

	public Classroom getClassroom(String id) {
		Classroom c = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from class where id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, id);
			ResultSet result = myStmt.executeQuery();
			if (result.next()) {
				c = new Classroom(Integer.parseInt(id), result.getString("name"));
			}
			String sql1 = "SELECT * FROM user WHERE idrole=1 AND idClass=?";
			PreparedStatement myStmt1 = myConn.prepareStatement(sql1);
			myStmt1.setString(1, id);
			ResultSet res = myStmt1.executeQuery();
			while (res.next()) {
				User u = new User(res.getString("name"), res.getString("lastname"), res.getString("username"),
						res.getString("email"));
				c.addEleve(u);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
		return c;
	}

	public Classroom getClassroomByName(String name) {
		Classroom c = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from class where name=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, name);
			ResultSet result = myStmt.executeQuery();
			if (result.next()) {
				c = new Classroom(Integer.parseInt(result.getString("id")), name);
			}
			String sql1 = "SELECT * FROM user WHERE idrole=1 AND idClass=?";
			PreparedStatement myStmt1 = myConn.prepareStatement(sql1);
			myStmt1.setString(1, Integer.toString(c.getId()));
			ResultSet res = myStmt1.executeQuery();
			while (res.next()) {
				User u = new User(res.getString("name"), res.getString("lastname"), res.getString("username"),
						res.getString("email"));
				c.addEleve(u);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
		return c;
	}

	public void updateTodo(Todo t, PrivateKey key) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "update todo set description=? where id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, t.getDescription());
			myStmt.setString(2, Security.decrypt(t.getId(), key));
			myStmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
	}

	public void updateClass(String id, String name) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "update class set name=? where id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, name);
			myStmt.setString(2, id);
			myStmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
	}

	public void addTodo(Todo t, String classroom) throws SQLException {
		Connection myConn = dataSource.getConnection();
		try {
			String sql = "INSERT INTO todo(description,idInstructor) VALUES (?,?)";
			PreparedStatement preparedStmt = myConn.prepareCall(sql);
			preparedStmt.setString(1, t.getDescription());
			preparedStmt.setString(2, Integer.toString(t.getIdinstructor().getId()));
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		Todo todo = null;
		PreparedStatement myStmt = null;
		try {
			String sql = "select * from todo where idInstructor=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, Integer.toString(t.getIdinstructor().getId()));
			ResultSet result = myStmt.executeQuery();
			if (result.next()) {
				todo = new Todo(result.getString("id"), result.getString("description"), t.getIdinstructor());
			}
		} catch (Exception e) {
			System.err.println("ERR SELECT");
			System.err.println(e.getMessage());
		}

		try {
			String sql2 = "INSERT INTO todoclass(idclass,idtodo) VALUES (?,?)";
			PreparedStatement preparedStmt = myConn.prepareCall(sql2);
			preparedStmt.setString(1, Integer.toString(getClassroomByName(classroom).getId()));
			preparedStmt.setString(2, todo.getId());
			preparedStmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.err.println("INSERT 2");
			System.err.println(e.getMessage());
		}
	}

	public void addStudent(String username, String email, String id, String name, String lastname) throws SQLException {
		Connection myConn = dataSource.getConnection();
		try {
			String sql = "INSERT INTO user(username, password, email, idrole, idclass, name, lastname) VALUES (?,?,?,1,?,?,?)";
			PreparedStatement preparedStmt = myConn.prepareCall(sql);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, username);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, id);
			preparedStmt.setString(5, name);
			preparedStmt.setString(6, lastname);
			preparedStmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public void addClass(Classroom c) throws SQLException {
		Connection myConn = dataSource.getConnection();
		try {
			String sql = "INSERT INTO class(name) VALUES(?)";
			PreparedStatement preparedStmt = myConn.prepareCall(sql);
			preparedStmt.setString(1, c.getName());
			preparedStmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public void removeTodo(int id) throws SQLException {
		Connection myConn = dataSource.getConnection();
		try {
			String sql = "DELETE FROM todo WHERE id=?";
			PreparedStatement preparedStmt = myConn.prepareCall(sql);
			preparedStmt.setInt(1, id);
			preparedStmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.err.println("Goot a exception");
		}
	}
	
	public void archiveClass(String id) throws SQLException {
		Connection myConn = dataSource.getConnection();
		try {
			String sql = "UPDATE class SET archivage=true WHERE id=?";
			PreparedStatement preparedStmt = myConn.prepareCall(sql);
			preparedStmt.setString(1, id);
			preparedStmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.err.println("Goot a exception");
		}
	}

	public void updateUser(User u, String username, String email, String pwd) throws SQLException {
		Connection myConn = dataSource.getConnection();
		try {
			String sql = "UPDATE user SET username=?,email=?,password=? WHERE id=?";
			PreparedStatement preparedStmt = myConn.prepareCall(sql);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, Security.sha1(pwd));
			preparedStmt.setInt(4, u.getId());
			preparedStmt.executeUpdate();
			myConn.close();
		} catch (Exception e) {
			System.err.println("Goot a exception");
		}
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
