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

import com.todoapp.web.jdbc.TododbUtil;

/**
 * Servlet implementation class addStudentControllerServlet
 */
@WebServlet("/addStudentControllerServlet")
public class addStudentControllerServlet extends HttpServlet {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("idClass");
		String username = (String) request.getParameter("username");
		String name = (String) request.getParameter("name");
		String lastname = (String) request.getParameter("lastname");
		String mail = (String) request.getParameter("mail");
		try {
			tododbutil.addStudent(username, mail, id, name, lastname, (PublicKey) session.getAttribute("publickey"));
			;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/editClassroomController?classeId=" + id);
	}

}
