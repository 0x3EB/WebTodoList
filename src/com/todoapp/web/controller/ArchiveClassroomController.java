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
 * Servlet implementation class ArchiveClassroomController
 */
@WebServlet("/ArchiveClassroomController")
public class ArchiveClassroomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TododbUtil tododbutil;
	private String id;

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
		id = request.getParameter("classeId");
		try {
			tododbutil.archiveClass(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("ClassroomControllerServlet");
	}

}
