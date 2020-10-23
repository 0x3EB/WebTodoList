package com.todoapp.web.entities;

import java.util.ArrayList;
import java.util.List;

public class Todo {
	private int id;
	private String description;
	private User idinstructor;
	private List<User> studentsDone;

	public Todo(int id, String description, User idinstructor) {
		this.id = id;
		this.description = description;
		this.idinstructor = idinstructor;
		this.studentsDone = new ArrayList<User>();
	}

	public Todo(String description, User idinstructor) {
		this.description = description;
		this.idinstructor = idinstructor;
		this.studentsDone = new ArrayList<User>();
	}

	public Todo(int id, String description, User idinstructor, List<User> studentsDone) {
		this.id = id;
		this.description = description;
		this.idinstructor = idinstructor;
		this.studentsDone = studentsDone;
	}

	public Todo(String description, User idinstructor, List<User> studentsDone) {
		this.description = description;
		this.idinstructor = idinstructor;
		this.studentsDone = studentsDone;
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

	public List<User> getStudentsDone() {
		return studentsDone;
	}

	public void addStudentsDone(User u) {
		studentsDone.add(u);
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", idinstructor=" + idinstructor + "]";
	}

}
