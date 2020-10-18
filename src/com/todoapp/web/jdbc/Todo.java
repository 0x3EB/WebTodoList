package com.todoapp.web.jdbc;

public class Todo {
	private int id;
	private String description;
	private User idinstructor;

	public Todo(int id, String description, User idinstructor) {
		this.id = id;
		this.description = description;
		this.idinstructor = idinstructor;
	}

	public Todo(String description, User idinstructor) {
		this.description = description;
		this.idinstructor = idinstructor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getIdinstructor() {
		return idinstructor;
	}
	
	public void setIdinstructor(User idinstructor) {
		this.idinstructor = idinstructor;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", idinstructor=" + idinstructor + "]";
	}

}
