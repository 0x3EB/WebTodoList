package com.todoapp.web.jdbc;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private Role idrole;
	private Classroom idclass;
	private List<Classroom> classes;

	public User(int id, String username, String password, Role idrole) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.idrole = idrole;
	}
	
	public User(int id, String username, String password, Role idrole, List<Classroom> listClass) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.classes = listClass;
	}
	
	public User(String username, String password, Role idrole) {
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.classes = new ArrayList<>();
	}

	public User(String username, String password, Role idrole, Classroom idclass) {
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.idclass = idclass;
		this.classes = new ArrayList<>();
	}

	public User(String username, String password, Role idrole, Classroom idclass, String email) {
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.idclass = idclass;
		this.email = email;
		this.classes = new ArrayList<>();
	}

	public User(String username, String password, Role idrole, String email) {
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.email = email;
		this.classes = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getIdrole() {
		return idrole;
	}

	public void setIdrole(Role idrole) {
		this.idrole = idrole;
	}

	public Classroom getIdclass() {
		return idclass;
	}

	public void setIdclass(Classroom idclass) {
		this.idclass = idclass;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + " " + classes.size()+"]";
	}

	public void addClass(Classroom Class) {
		classes.add(Class);
	}
	
	public List<Classroom> getClasses() {
		return classes;
	}
}
