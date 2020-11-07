package com.todoapp.web.controller;

import java.io.IOException;
import java.security.PublicKey;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.todoapp.web.entities.User;
import com.todoapp.web.jdbc.TododbUtil;

/**
 * Servlet implementation class DoneTodoControllerServlet
 */
@WebServlet("/DoneTodoControllerServlet")
public class DoneTodoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TododbUtil tododbutil;

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
		try {
			HttpSession session = request.getSession();
			request.setAttribute("TODO_LIST", tododbutil.getStudentTodoDone((User) session.getAttribute("user"),
					(PublicKey) session.getAttribute("publickey")));
			request.getRequestDispatcher("/user.jsp").forward(request, response);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
