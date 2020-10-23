package com.todoapp.web.jdbc;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	private int id;
	private String name;
	private List<User> eleves;
	private List<Todo> todos;

	public Classroom(int id, String name) {
		this.id = id;
		this.name = name;
		this.eleves = new ArrayList<>();
		this.todos = new ArrayList<>();
	}

	public Classroom(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Class [id=" + id + ", name=" + name + "]";
	}

	public void addEleve(User eleve) {
		eleves.add(eleve);
	}

	public List<User> getEleves() {
		return eleves;
	}

	public void addTodo(Todo todo) {
		todos.add(todo);
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public int getNumberOfStudents() {
		return this.eleves.size();
	}
}
