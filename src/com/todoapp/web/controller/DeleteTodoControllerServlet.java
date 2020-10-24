package com.todoapp.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.todoapp.web.jdbc.TododbUtil;

/**
 * Servlet implementation class DeleteTodoControllerServlet
 */
@WebServlet("/DeleteTodoControllerServlet")
public class DeleteTodoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TododbUtil tododbutil;
	private int id;

	@Resource(name = "jdbc/todolist")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		tododbutil = new TododbUtil(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("todoId"));
		try {
			tododbutil.removeTodo(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("UserControllerServlet");
	}

}
