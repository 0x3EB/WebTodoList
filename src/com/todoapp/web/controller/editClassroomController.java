package com.todoapp.web.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.todoapp.web.entities.Classroom;
import com.todoapp.web.jdbc.TododbUtil;

/**
 * Servlet implementation class editClassroomController
 */
@WebServlet("/editClassroomController")
public class editClassroomController extends HttpServlet {
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
		HttpSession session = request.getSession();
		String id = request.getParameter("classeId");
		Classroom classe = null;
		session.setAttribute("idClass", id);
		classe = tododbutil.getClassroom(id);
		request.setAttribute("classe", classe);
		request.getRequestDispatcher("edit-class.jsp").forward(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			HttpSession session = request.getSession();
			String name = request.getParameter("name");
			String id = (String) session.getAttribute("idClass");
			tododbutil.updateClass(id, name);
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/ClassroomControllerServlet");
	}

}
