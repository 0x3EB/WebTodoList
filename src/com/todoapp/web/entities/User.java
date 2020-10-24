package com.todoapp.web.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String name;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private Role idrole;
	private Classroom idclass;
	private List<Classroom> classes;
	private List<Todo> todosDone;

	public User(int id, String name, String lastname, String username, String password, Role idrole) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.todosDone = new ArrayList<Todo>();
	}

	public User(int id, String name, String lastname, String username, String password, List<Todo> todosDone,
			Role idrole) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.todosDone = todosDone;
	}

	public User(int id, String name, String lastname, String username, String password, Role idrole,
			List<Classroom> listClass) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.classes = listClass;
	}

	public User(String name, String lastname, String username, String password, Role idrole) {
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.classes = new ArrayList<>();
	}

	public User(String name, String lastname, String username, String password, Role idrole, Classroom idclass) {
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.idclass = idclass;
		this.classes = new ArrayList<>();
	}

	public User(String name, String lastname, String username, String password, Role idrole, Classroom idclass,
			String email) {
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.idclass = idclass;
		this.email = email;
		this.classes = new ArrayList<>();
	}

	public User(String name, String lastname, String username, String password, Role idrole, String email) {
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.idrole = idrole;
		this.email = email;
		this.classes = new ArrayList<>();
	}
	
	public User(String name, String lastname, String username, String email) {
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public void addClass(Classroom Class) {
		classes.add(Class);
	}

	public List<Classroom> getClasses() {
		return classes;
	}

	public void addTodoDone(Todo t) {
		todosDone.add(t);
	}

	public List<Todo> getTodosDone() {
		return this.todosDone;
	}

	public int getNumberOfTodosDone() {
		return this.todosDone.size();
	}

	public int getNumberOfClassrooms() {
		return this.classes.size();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + " " + classes.size() + "]";
	}

}
