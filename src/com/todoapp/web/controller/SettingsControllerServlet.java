package com.todoapp.web.controller;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class settingsControllerServlet
 */
@WebServlet("/SettingsControllerServlet")
public class SettingsControllerServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/settings.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User sessionUser = (User) session.getAttribute("user");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		try {
			tododbutil.updateUser(sessionUser, username, email, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/UserControllerServlet");
	}

}
