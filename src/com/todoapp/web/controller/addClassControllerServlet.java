package com.todoapp.web.controller;

import java.io.IOException;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.todoapp.web.entities.Classroom;
import com.todoapp.web.jdbc.TododbUtil;

/**
 * Servlet implementation class addClassControllerServlet
 */
@WebServlet("/addClassControllerServlet")
public class addClassControllerServlet extends HttpServlet {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Classroom c = new Classroom(request.getParameter("classname"));
		try {
			tododbutil.addClass(c);			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/ClassroomControllerServlet");
	}

}
