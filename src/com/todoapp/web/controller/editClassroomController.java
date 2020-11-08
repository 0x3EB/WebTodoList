package com.todoapp.web.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.todoapp.web.entities.Classroom;
import com.todoapp.web.jdbc.TododbUtil;
import com.todoapp.web.security.Security;

/**
 * Servlet implementation class editClassroomController
 */
@WebServlet("/editClassroomController")
public class editClassroomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TododbUtil tododbutil;
	private String id;
	private String id2;

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
		id = request.getParameter("classeId");
		Classroom classe = null;
		session.setAttribute("idClass", id);
		try {
			id2 = Security.decrypt(id, (PrivateKey) session.getAttribute("privatekey"));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		classe = tododbutil.getClassroom(id, (PrivateKey) session.getAttribute("privatekey"));
		request.setAttribute("classe", classe);
		session.setAttribute("idClassNum", id2);
		request.getRequestDispatcher("edit-class.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			HttpSession session = request.getSession();
			String name = request.getParameter("name");
			String id = (String) session.getAttribute("idClass");
			tododbutil.updateClass(id, name, (PrivateKey) session.getAttribute("privatekey"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/ClassroomControllerServlet");
	}

}
